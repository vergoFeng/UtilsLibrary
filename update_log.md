* 17/12/13

    * AppUtils：修复launchApp方法空指针问题
    * BarUtils：新增setStatusBarColor、hideStatusBar方法
    * BitmapUtils：新增bytes2Bitmap方法
    * EncryptUtils：修复encryptAES2Base64、decryptBase64AES、decryptHexStringAES方法的空指针问题
    * 更细README.md
    * 发布1.1.2版本

* 17/11/08

    * TimeUtils：修复formatDate1方法，获取与当前时间差方法优化
    * JLog：优化相关功能，新增setStackDeep设置log栈深度，优化LogUtils多参数打印
    * PhoneUtils：修复getPhoneIMEI空指针异常
    * StringUtils：废弃isTrimEmpty方法，isEmpty和isSpace增加判断字符串为"null"
    * 更新README.md
    * 发布1.1.1版本

* 17/08/24

    * 发布1.1.0版本，更新README.md
    * 新增BarUtils、OsUtils、ToastUtils工具类
    * FileUtils：修复getDirLength方法空指针异常bug
    * StringUtils：优化containsEmoji方法、新增containsChinese方法
    * ScreenUtils：去除getStatusBarHeight方法

* 17/08/10 发布1.0.4版本，更新README.md
* 17/08/09 新增BitmapUtils、DoubleClickUtils、FileUtils
* 17/07/26 发布1.0.3版本，EncryptUtils新增AES加密解密。