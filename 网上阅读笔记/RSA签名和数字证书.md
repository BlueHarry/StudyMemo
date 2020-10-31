# RSA签名和数字证书

来自 boxueio.com

有了一对 RSA 公钥和密钥之后，其实我们能做的不仅仅是用公钥加密数据，并用私钥进行解密。理论上来说，反过来也行，即用私钥加密，用公钥解密。但这仅仅是一种理论上的行为，在实际环境中，这种做法毫无意义。因为按照 RSA 的设计，公钥是可以任意发布的。这也就意味着，用私钥加密的数据实质上没有任何安全性可言。但是，公钥可以解密一份特定的数据这件事情本身，却是有意义的。它能说明这份数据一定是由与之对应的私钥进行加密的，如果我们知道私钥的拥有者，也就确定了数据的来源。基于这样的推理，我们管用私钥加密数据的行为叫做数字签名，用公钥解密的行为叫做验证签名。但这仅仅还只是停留在推理的层面，真正要实施这个过程，还有不少细节要补充进来。

## 理解数字签名和验签的过程

出于 RSA 算法自身的限制，加密的数据不能太大，要小于计算过程中的 N。另外，数据大了之后，RSA 算法的性能也会受影响。因此，在对数据进行数字签名之前，RSA 会先使用两类单向散列算法，MD 和 SHA，对原始数据计算摘要。

其中：

- MD 算法中我们最熟悉的就是 MD5，实际上，它的前身还有 MD-2 / 3 / 4 散列算法；
- SHA 则包含了 SHA-0 / 1 / 2 / 3 这几类。SHA-0 和 SHA-1 已经被正式确认为不安全的算法，基于这些散列函数生成的数字签名也因此是不安全的。现如今，几乎所有主流操作系统和浏览器都将其包含的证书升级到了基于 SHA-2 的产品。这里要说一下的是，SHA-2 指的是 SHA-224、SHA-256、SHA-384 和 SHA-512 这四种散列算法的统称。而 SHA-3 则是在 2015 年新颁布的算法，它的目的并不是取代 SHA-2，只是在确认了 SHA-0 和 SHA-1 不安全之后，国际标准化组织认为需要一个新版本摘要算法罢了；

接下来，创建一个 a.txt，它包含的内容是 "Hello world"。先执行下面的命令查看 a.txt 执行散列的结果：

```
openssl sha512 a.txt
```

结果应该是这样的：

```
SHA512(a.txt)= b7f783baed8297f0db917462184ff4f08e69c2d5e5f79a942600f9725f58ce1f29c18139bf80b06c0fff2bdd34738452ecf40c488c22a7e3d80cdf6f9c1c0d47
```

也就是说，我们要进行数字签名的，是上面这一串数字。接下来，继续执行下面的命令对 SHA-512 后的结果进行签名：

```
openssl dgst -sha512 -sign private.pem -out sha512.signed a.txt
```

这里，生成的 sha512.signed 就是签名之后的结果了。它是二进制格式的，我们无法直接查看它的内容。

接下来，按照流程，我们就可以把 a.txt 和 sha512.signed 一起，发给需要的人了。而拿到这两个文件的人显然也是持有我们颁发的公钥的，因此他可以执行下面的命令对 a.txt 的内容进行验证：

```
openssl dgst -sha512 -verify public.pem -signature sha512.signed a.txt
```

要注意的是签名和验签要使用相同的散列算法，这是提供数字签名的一方应该和签名文件一起公开的内容。如果验签成功了，应该在终端看到 "Verified OK" 的提示。这样，就可以确信收到的 a.txt 的内容完整无误了。

不过，通过这种方式建立的信任，其实远没有看上去的那么可靠。这种信任是建立在私钥和公钥都是安全的这个前提下的。假设验签的一方的公钥被恶意替换成了另外一个人的。这个人就可以以我们的名义发布内容，这就完全没有信任和安全可言了。

## CA 和 数字证书

为了解决这个问题，人们对这套模型做了两方面的改进：

- 一方面制定了特定的标准，给公钥中加入了一些关于签名方身份的信息，这种加入了信息的公钥，就是我们说的数字证书，而这个标准，叫做 X.509 v3，稍后就会看到，它也是采用了 ASN.1 格式进行编码的；
- 另一方面，限制了可以颁发可信数字证书的机构，也就是 CA。简单来说，就是数字证书只能由具有足够公信力的组织进行颁发。打开 mac OS 的 Keychain Access，在 System Roots 里，可以看到 mac OS 中内置的，由 根CA 颁发的证书。简单来说，由这些 CA 以及被这些 CA 认证过的组织颁发的证书，mac OS 默认都是可信的；

![img](https://image.boxueio.com/m-rsa-sign-1@2x.jpg)

那么，数字证书中究竟包含了什么呢？我们通过自己给自己签发一张数字证书，来观察一下。首先，执行下面的命令，给 CA 自己生成一对密钥：

```
openssl genrsa -out ca_private.pem 2048
openssl rsa -in ca_private.pem -outform PEM -pubout -out ca_public.pem
```

其次，基于 CA 的私钥，为 CA 生成一份用于识别自己的数字证书：

```
openssl req -new -x509 -key ca_private.pem -out ca.crt -days 3650
```

接下来，终端会提示我们输入一系列描述 CA 身份的信息，最终生成的 ca.crt 就是 CA 自己的证书文件。这样，我们就成为了一个可以颁发证书的 CA 了，只是，我们颁发的证书除了自己之外，没人信而已 :)

接下来，如果有人希望让我们为他颁发证明他身份的数字证书，他需要执行下面的命令，基于自己的私钥生成一份叫做 CSR 的文件：

```
openssl req -new -key private.pem -out me.csr
```

同样，终端会要求他输入要证明身份的信息。在生成的 me.csr 文件中，包含了这些信息，以及用户的公钥。

接下来，他把 me.csr 提交给我们，我们执行下面的命令，就可以给用户颁发证书了：

```
openssl x509 -req -days 3650  \
  -in me.csr -CA ca.crt -CAkey ca_private.pem \
  -CAcreateserial -out me.crt
```

这里，生成的 me.crt 就是可以证明用户身份的数字证书。现在，他就可以把原始文件和证书一起发布给需要的人。拿到这些文件的人，就可以先执行下面的命令查看证书的信息：

```
openssl x509 -in me.crt -text -noout
```

确认证书无误之后，再执行下面的命令对文件验签：

```
openssl dgst -sha512 \
  -verify <(openssl x509 -in me.crt -pubkey -noout) \
  -signature sha512.signed a.txt
```

这时，如果可以通过验证，就可以确保 a.txt 的内容以及来源了。

## 常见的数字证书格式

在这一节最后，我们来看看常用的数字证书格式、扩展名，以及相关的转换命令。和 RSA 密钥对类似，证书也有 base64 明文格式 (PEM 编码) 以及 ASN.1 编码 (DER编码) 的二进制格式。并且，证书的扩展名和证书文件的格式没有必然的联系，只和操作系统相关。例如：

- 我们刚才自己颁发的 .crt 扩展名多用于 Unix / BSD / Linux 等操作系统；
- .cer 多用于 Windows 操作系统；

这两种扩展名都可以保存 PEM 编码或 DER 编码的证书。对于 PEM 编码，开头和结尾的标记是这样的：

```
-----BEGIN CERTIFICATE-----
MIIDhDCCAmwCCQD9fSsKdb+A9DANBgkqhkiG9w0BAQUFADCBgzELMAkGA1UEBhMC
Q04xEDAOBgNVBAgMB0JlaWppbmcxEDAOBgNVBAcMB0JlaWppbmcxDjAMBgNVBAoM
...
9I6Z8ek2i38Oe+o2eZAMPzTDq+MepqSJkOeJOkPNB5v+DlEoaoeqWs3ucfJEo4O9
EVPVsK+iY9N+GyoLTbQg3fwpj/jRPDY0Y6fqTqxO2LPBkAPVV56+6Q==
-----END CERTIFICATE-----
```

我们可以通过 openssl 工具在两种格式之间进行转换：

```
# DER -> PEM
openssl rsa -inform DER -in key.der -out key.pem
# PEM -> DER
openssl rsa -inform PEM -in key.pem -outform DER -out key.der
```

## What's next?

以上，就是关于数字签名和验签的功能，尽管暂时我们还用不到这部分内容，但作为 RSA 应用的一部分，我们还是选择在这里向大家说明了一下。目的是希望大家理解 RSA 用于加密和数字签名的两种相似却又不同的模型，能用对它们，才能起到正确的安全效果。理解了这些内容之后，下一节，我们就通过 RSA 加密客户端发起请求时传递的参数。