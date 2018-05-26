* 18/05/26

    * BarUtils：修复5.0手机，魅族和小米手机状态栏在设置白色情况下变灰色问题
    * 更新README.md
    * 发布1.2.4版本

* 18/04/28

    * 修复多个空指针异常问题
    * JLog：优化展现效果
    * TimeUtils：新增formatDate方法，可自定义时间转换类型
    * 更新README.md
    * 发布1.2.3版本

* 18/04/03

    * BarUtils：修复6.0以上手机状态栏字体颜色转换问题
    * 更新README.md
    * 发布1.2.2版本

* 18/04/03

    * BarUtils：优化setStatusBarColor方法
    * FileUtils：URI转文件路径方法公有化
    * SPUtils：修改SharedPreferences默认文件名为"utils_sp"
    * 新增CHM.md帮助文档
    * 修改README.md中错误
    * 发布1.2.1版本

* 18/03/22

    * 新增DeviceUtils设备相关工具类
    * BarUtils：完善setStatusBarColor、hideStatusBar方法，可对view进行沉浸式处理
    * FileUtils：新增getPathFromUri、getFileFromUri方法，根据Uri获取文件的真实路径
    * NetworkUtils：新增getIPAddress方法，获取IP地址
    * StringUtils：完善手机号（精确）的正则表达式
    * 更新README.md
    * 发布1.2.0版本

* 17/12/13

    * AppUtils：修复launchApp方法空指针问题
    * BarUtils：新增setStatusBarColor、hideStatusBar方法
    * BitmapUtils：新增bytes2Bitmap方法
    * EncryptUtils：修复encryptAES2Base64、decryptBase64AES、decryptHexStringAES方法的空指针问题
    * 更新README.md
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