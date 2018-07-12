### 1. AppUtils（App相关）

* 获取App包名

  ```
  String getAppPackageName()

  //示例
  String appPcakageName = AppUtils.getAppPackageName();
  ```

* 判断App是否安装

  ```
  /**
   * @param packageName 包名
   * @return 
   *     true : 已安装
   *     false: 未安装
   */
  boolean isInstallApp(String packageName)

  //示例
  boolean isInstalled = AppUtils.isInstallApp("com.tencent.mm");
  ```

* 打开App

  ```
  /**
   * @param packageName 包名
   */
  launchApp(String packageName)

  //示例
  AppUtils.launchApp("com.tencent.mm");
  ```

### 2. BarUtils（状态栏相关）

* 设置状态栏颜色

  ```
  /**
   * @param activity activity
   * @param color    状态栏颜色值
   * @param dark     是否把状态栏文字及图标颜色设置为深色
   */
  setStatusBarColor(@NonNull final Activity activity, @ColorInt final int color, boolean dark)

  /**
   * @param activity  activity
   * @param viewGroup viewGroup
   * @param color     状态栏颜色值
   * @param dark      是否把状态栏文字及图标颜色设置为深色
   */
  setStatusBarColor(@NonNull final Activity activity, ViewGroup viewGroup, @ColorInt final int color, boolean dark)
  ```

* 隐藏状态栏

  ```
  /**
   * @param activity activity
   * @param dark     是否把状态栏文字及图标颜色设置为深色
   */
  hideStatusBar(@NonNull final Activity activity, boolean dark) 
  ```

* 获取状态栏高度

  ```
  /**
   * @return 状态栏高度px
   */
  int getStatusBarHeight()
  ```

* 设置状态栏透明(api大于19方可使用)

  ```
  /**
   * @param activity 当前Activity
   */
  setTranslucentStatus(Activity activity)
  ```

* 设置状态栏文字及图标颜色（6.0以上版本，系统方法）

  ```
  /**
   * @param activity 当前Activity
   * @param dark     是否把状态栏文字及图标颜色设置为深色
   */
  setStatusBarMode(Activity activity, boolean dark)
  ```

* 设置状态栏文字及图标颜色（魅族）

  ```
  setStatusBarModeByFlyme(Activity activity, boolean dark)
  ```

* 设置状态栏文字及图标颜色（小米，MIUIV6以上）

  ```
  setStatusBarModeByMIUI(Activity activity, boolean dark)
  ```

### 3. BitmapUtils（图像操作相关）

* bitmap转byte[]

      /**
       * @param bitmap bitmap对象
       * @param format 格式
       * @return 字节数组
       */
      byte[] bitmap2Bytes(Bitmap bitmap, CompressFormat format)
      
      //示例
      byte[] bitmapByte = BitmapUtils.bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG);

* byte[]转bitmap

   ```
   /**
    * @param bytes 字节数组
    * @return bitmap
    */
   Bitmap bytes2Bitmap(final byte[] bytes)

   //示例
   Bitmap bitmap = BitmapUtils.bytes2Bitmap(bitmapByte);
   ```


* 获取Bitmap

      /**
       * @param filePath  文件路径
       * @param maxWidth  最大宽度
       * @param maxHeight 最大高度
       * @return bitmap
       */
      Bitmap getBitmap(String filePath, int maxWidth, int maxHeight)
      
      //示例
      Bitmap bitmap = BitmapUtils.getBitmap(filePaht, 100, 100);

* 保存图片

   ```
   /**
    * @param src      源图片
    * @param filePath 要保存到的文件路径
    * @param file     要保存到的文件
    * @param format   格式
    * @param recycle  是否回收
    * @return
    *     true : 成功
    *     false: 失败
    */
   boolean save(final Bitmap src, final String filePath, final CompressFormat format)

   boolean save(final Bitmap src, final File file, final CompressFormat format)

   boolean save(final Bitmap src, final String filePath, final CompressFormat format, final boolean recycle)

   boolean save(final Bitmap src, final File file, final CompressFormat format, final boolean recycle) 
   ```

### 4. DensityUtils（尺寸相关）

* dp转px

  ```
  /**
   * @param dpValue dp值
   * @return px值
   */
  int dp2px(float dpValue)
  ```


* px转dp

  ```
  /**
   * @param pxValue px值
   * @return dp值
   */
  int px2dp(float pxValue)
  ```

* sp转px

  ```
  /**
   * @param spValue sp值
   * @return px值
   */
  int sp2px(float spValue)
  ```

* px转sp

  ```
  /**
   * @param pxValue px值
   * @return sp值
   */
  int px2sp(float pxValue)
  ```

### 5. DeviceUtils（设备相关）

* 获取设备系统版本号

  ```
  /**
   * @return 设备系统版本号
   */
  String getSDKVersionName()
  ```

* 获取设备系统版本码

  ```
  int getSDKVersionCode()
  ```

* 获取设备厂商

  ```
  String getManufacturer()
  ```

* 获取设备型号

  ```
  String getModel()
  ```



### 6. DoubleClickUtils（判断双击相关）

	//默认时间间隔500ms
	boolean isDoubleClick()
	
	/**
	 * 判断是否间隔时间内双击
	 * @param doubleTime  自定义判断双击的时间间隔，单位ms
	 * @return
	 *     true : 间隔时间内两次点击
	 *     false: 超出间隔时间两次点击
	 */
	boolean isDoubleClick(int doubleTime) {

### 7. EncryptUtils（加密解密相关）

* MD5加密

  ```
  /**
   * @return 16进制密文
   */
  String encryptMD5ToString(String data)
  ```

* AES加密解密

    > 加密解密模式为CBC，key为秘钥，iv为向量

    ```
    /**
     * AES加密后转为Base64编码
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return Base64编码后密文字符串
     */
    String encryptAES2Base64(String data, String key, String iv)

    /**
     * AES加密后转为16进制
     * @param data 明文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 16进制密文字符串
     */
    String encryptAES2HexString(String data, String key, String iv)

    /**
     * AES解密Base64编码密文
     *
     * @param data Base64编码密文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 明文字符串
     */
    String decryptBase64AES(final String data, final String key, final String iv)

    /**
     * AES解密16进制密文
     *
     * @param data 16进制密文
     * @param key  16、24、32字节秘钥
     * @param iv   向量
     * @return 明文字符串
     */
    String decryptHexStringAES(final String data, final String key, final String iv)
    ```

### 8. FileUtils（文件相关）

* 根据文件路劲获取文件

  ```
  File getFileByPath(String filePath)
  ```

* 判断文件是否存在

  ```
  /**
   * @param filePath 文件String路径
   * @param file     文件File
   * @return
   *     true : 存在
   *     false: 不存在
   */
  boolean isFileExists(final String filePath)
  boolean isFileExists(final File file)
  ```

* 获取目录大小

  ```
  /**
   * @param dirPath 目录String路径
   * @param dir     目录File
   * @return 目录长度
   */
  long getDirLength(final String dirPath)
  long getDirLength(final File dir)
  ```

* 获取文件大小

  ```
  /**
   * @param filePath 文件String路径
   * @param file     文件File
   * @return 目录长度
   */
  long getFileLength(final String filePath)
  long getFileLength(final File file)
  ```

* 复制目录

  ```
  /**
   * @param srcDirPath  源目录路径
   * @param destDirPath 目标目录路径
   * @return
   *     true : 复制成功
   *     false: 复制失败
   */
  boolean copyDir(final String srcDirPath, final String destDirPath)
  /**
   * @param srcDir  源目录
   * @param destDir 目标目录
   */
  boolean copyDir(final File srcDir, final File destDir)
  ```


* 复制文件

  ```
  /**
   * @param srcFilePath  源文件路径
   * @param destFilePath 目标文件路径
   * @return
   *     true : 复制成功
   *     false: 复制失败
   */
  boolean copyFile(final String srcFilePath, final String destFilePath)
  /**
   * @param srcFile  源文件
   * @param destFile 目标文件
   */
  boolean copyFile(final File srcFile, final File destFile)
  ```

* 删除目录

  ```
  /**
   * @param dirPath  目录String路径
   * @param dir      目录File
   * @return
   *     true : 删除成功
   *     false: 删除失败
   */
  boolean deleteDir(final String dirPath)
  boolean deleteDir(final File dir)
  ```

* 删除文件

  ```
  /**
   * @param srcFilePath  文件String路径
   * @param file         文件File
   * @return
   *     true : 删除成功
   *     false: 删除失败
   */
  boolean deleteFile(final String srcFilePath)
  boolean deleteFile(final File file)
  ```
### 9. InputMethodUtils（键盘相关）

* 动态显示键盘

  ```
  showSoftInput(Activity activity)
  showSoftInput(View view)
  ```

* 动态隐藏键盘

  ```
  hideSoftInput(Activity activity)
  hideSoftInput(View view)
  ```

* 触摸空白区域隐藏键盘

  ```
  /**
   * @param context 上下文
   * @param v edittext
   * @param event 重写dispatchTouchEvent方法中的event
   */
  touchHideSoftInput(Context context, View v, MotionEvent event)
  ```

  示例：在activity中重写dispatchTouchEvent方法，调用方法。

  ```
  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
      //View v = getCurrentFocus();
  	InputMethodUtils.touchHideSoftInput(this, editView, ev);
      return super.dispatchTouchEvent(ev);
  }
  ```
### 10. IntentUtils（Intent意图相关）
* 获取跳至拨号界面意图

  ```
  Intent getDialIntent(String phoneNumber)
  ```

* 获取拨打电话意图

  ```
  Intent getCallIntent(String phoneNumber)
  ```

  > 需要获取权限<uses-permission android:name="android.permission.CALL_PHONE"/>


* 获取跳至发送短信界面的意图

   ```
   /**
    * @param phoneNumber 接收号码
    * @param content     短信内容
    */
   Intent getSendSmsIntent(String phoneNumber)
   Intent getSendSmsIntent(String phoneNumber, String content)
   ```

* 获取跳至发邮件的意图

  ```
  /**
   * @param mailAdress 接收邮箱地址
   */
  Intent getSendMailIntent(String mailAdress)
  ```

* 获取打开相机拍照的意图

  > 以兼容Android 7.0

  ```
  /**
   * @param file 拍照保存图片的文件
   */
  Intent getCameraIntent(File file)
  ```

  需要获取权限

  ```
  <uses-permission android:name="android.permission.CAMERA"/>
  <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
  ```

### 11. JLog（日志相关）

### 11.1 功能
* 可设置Log开启和关闭
* 可设置Log全局Tag，全局Tag为空时Tag为当前类名
* 可设置Log是否显示头部信息
	* Log头部含有当前线程名
	* Log头部含有当前类及行号和函数名，支持点击跳转
* 可设置Log是否显示边框
* 支持多参数输出
* 支持JSON串的输出
### 11.2 使用
#### 11.2.1 初始化
在Application的onCreate()中进行初始化

	@Override
	public void onCreate() {
	    super.onCreate();
	    JLog.Builder builder = new JLog.Builder();
	}
当然，JLog还支持多参数配置，具体如下

	new JLog.Builder()
	    .setLogSwitch(BuildConfig.DEBUG) //设置log总开关，默认为true
	    .setGlobalTag("fhj")    //设置log全局标签，默认为null
	                            //当全局标签不为空时，我们输出的log全部为该tag，
	                            //为空时，如果传入的tag为空那就显示类名，否则显示tag
	    .setLogHeadSwitch(true)	//设置log头信息开关，默认为true
	    .setBorderSwitch(true)	//输出日志是否带边框开关，默认true
	    .setStackDeep(1)        //设置log栈深度，默认为1

#### 11.2.2 使用示例
* 基础用法：`JLog.d("==onCreate==");`

  ![](https://i.imgur.com/b5cYp5x.png)

* 自定义tag：`JLog.dTag("customTag", "==initView==");`

  ![](https://i.imgur.com/moOljkh.png)

* 多参数输出：`JLog.d("==content1==", "==content2==", "==content3==");`

  ![](https://i.imgur.com/AunFLu9.png)

* json输出：`JLog.json(json);` （默认在Debug日志）

  ![](https://i.imgur.com/Z17rLz5.png)

  其余json相关方法：

  	json(String contents)
  	json(@TYPE int type, String contents)
  	json(String tag, String contents)
  	json(@TYPE int type, String tag, String contents)

### 12. NetworkUtils（网络相关）

> 需要添加权限<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

* 判断网络是否连接

  ```
  boolean isConnected()
  ```

* 判断wifi是否连接状态

  ```
  boolean isWifiConnected()
  ```

* 获取当前网络类型

  ```
  /**
   * @return
   *     "Wi-Fi"
   *     "4g"
   *     "3g"
   *     "2g"
   *     "unknown"
   *     "none"
   */
  String getNetworkType()
  ```

* 获取 IP 地址

  > 需要添加权限<uses-permission android:name="android.permission.INTERNET"/>

  ```
  /**
   * @param useIPv4 True to use ipv4, false otherwise.
   * @return the ip address
   */	
  String getIPAddress(final boolean useIPv4)
  ```

### 13. OsUtils（手机系统相关）

* 判断手机系统是否是小米MIUI

  `boolean isMIUI()`

* 判断手机系统是否是魅族Flyme

  `boolean isFlyme()`

### 14. PhoneUtils（手机相关）

> 需要添加权限<uses-permission android:name="android.permission.READ_PHONE_STATE"/>

* 获取手机IMEI码

  ```
  String getPhoneIMEI()
  ```

* 获取Sim卡运营商名称

  ```
  /**
   * @return 移动网络运营商名称
   *         "中国电信"
   *         "中国移动"
   *         "中国联通"
   *         "未知"
   */
  String getSimOperatorByMnc()

  /**
   * @return 移动网络运营商名称转换成数字标识
   *         "1"：中国电信
   *         "2"：中国移动
   *         "3"：中国联通
   *         "4"：未知
   */
  public static String getSimOperatorCode()
  ```

### 15. ScreenUtils（屏幕相关）

* 获取屏幕的宽度

  `int getScreenWidth()`

* 获取屏幕的高度

  `int getScreenHeight()`

* 设置全屏

  ```
  /**
   * @param activity 当前Activity
   */
  setFullScreen(Activity activity)
  ```

  > 注意：一定要在setContentView之前调用，否则报错

* 设置为横竖屏

  ```
  /**
   * 设置为横屏
   */
  void setLandscape(Activity activity)
  /**
   * 设置为竖屏
   */
  void setPortrait(Activity activity)
  ```

* 判断是否横竖屏

  ```
  /**
   * 判断是否是横屏
   */
  boolean isLandscape()
  /**
   * 判断是否是竖屏
   */
  boolean isPortrait()
  ```

* 判断是否为平板

  ```
  /**
   * 判断是否为平板
   */
  boolean isTablet()
  ```

### 16. SDCardUtils（SD卡相关）

* 判断SD卡是否可用：

    ```
    boolean isSDCardEnable()
    ```

* 获取应用专属缓存目录

    ```
    File getCacheDirectory(Environment.DIRECTORY_DOWNLOADS);
    String getCachePath(Environment.DIRECTORY_DOWNLOADS);
    ```

    方法参数：

    ```
    Environment.DIRECTORY_MUSIC
    Environment.DIRECTORY_PODCASTS
    Environment.DIRECTORY_RINGTONES
    Environment.DIRECTORY_ALARMS
    Environment.DIRECTORY_NOTIFICATIONS
    Environment.DIRECTORY_PICTURES
    Environment.DIRECTORY_MOVIES
    ```

* 获取SD卡的根目录

    ```
    File getStorageDirectory();
    String getStoragePath();
    ```

### 17. SPUtils（SharedPreferences相关）

### 17.1 功能
* 保存数据
* 链式调用一次保存多条数据
* 读取数据
* 获取所有键值对
* 移除某个键对应的数据
* 清除所有数据

### 17.2 使用
* 获取SPUtils对象

   ```
   //默认SharedPreferences文件名，"utils_sp"
   SPUtils init();

   //自定义sharedpreferences的文件名
   SPUtils init(String spName);
   ```

* 保存数据

   ```
   //基础使用
   SPUtils.init().putBoolean("bool", true);

   //可以使用字符串资源作为保存的键
   SPUtils.init().putBoolean(R.string.key_bool, true);

   //自动转换存储类型
   SPUtils.init().put("bool", true);
   ```

* 链式调用一次保存多条数据

   ```
   SPUtils.init()
          .putBoolean(R.string.key_bool, true)
          .putInt(R.string.key_int, 1)
          .putString(R.string.key_string, "string")
          .putLong(R.string.key_long, 1000000000)
          .putFloat(R.string.key_float, 1.1f)
          .put("put", 100)
          .putStringSet(R.string.key_set, strings);
   ```

* 读取数据

   ```
   //基础使用
   boolean booleanData = SPUtils.init().getBoolean("bool");

   //使用字符串资源作为键
   boolean booleanData = SPUtils.init().getBoolean(R.string.key_bool, defValue);

   //转换类型
   boolean booleanData = (Boolean) SPUtils.init().get("bool", true);
   ```

* 移除某个键对应的数据

   ```
   SPUtils.init().remove(bool);
   //使用字符串资源作为键
   SPUtils.init().remove(R.string.key_bool);
   ```

* 清除所有数据

    ```
    SPUtils.init().clear();
    ```

### 18. StringUtils（字符串相关）

* 判断字符串是否为null或长度为0

    `boolean isEmpty(CharSequence s)`

* 判断字符串是否为null或全为空格

    `boolean isTrimEmpty(CharSequence s)`

* 判断字符串是否为null或全为空白字符

    `boolean isSpace(CharSequence s)`

* 验证手机号（简单）

    `boolean isMobileSimple(CharSequence s)`

* 验证手机号（精确）

    `boolean isMobileExact(CharSequence s)`

* 验证身份证号码（18位）

    `boolean isIDCard(CharSequence s)`

* 验证邮箱

    `boolean isEmail(CharSequence s)`

* 判断输入是否是表情

    `boolean containsEmoji(CharSequence s)`

* 判断字符串是否包含中文

    `boolean containsChinese(String s)`

### 19. TimeUtils（时间相关）

* 获取与当前时间的差

  ```
  /**
   * @param time 毫秒时间戳
   * @return 友好型与当前时间的差
   *         如果在1小时内，显示XXX分钟前
   *         如果在今天内，显示XXX小时前
   *         如果是昨天的，显示昨天
   *         其余显示，2018-04-03
   *         时间不合法的情况全部日期和时间信息，如星期二 四月 03 14:21:20 CST 2018
   */
  String formatDate1(long time)
  ```

* 几种常用转换格式

  ```
  /**
   * 日期转换格式：yyyy-MM-dd
   * @param t long型时间
   * @return 2018-04-03
   */
  String formatDate2(long t)

  /**
   * 日期转换格式：yyyy年MM月dd日
   * @param t long型时间
   * @return 2018年04月03日
   */
  String formatDate3(long t)

  /**
   * 日期转换格式：yyyy-MM-dd HH:mm:ss
   * @param t long型时间
   * @return 2018-04-03 09:01:01
   */
  String formatDate4(long t)
  ```

* string类型转换为date类型

  ```
  /**
   * ps：strTime的时间格式必须是yyyy-MM-dd HH:mm:ss
   * @param strTime 要转换的string类型的时间
   * @return date类型
   */
  Date stringToDate(String strTime)
  ```

* string类型转换为long类型

  ```
  /**
   * ps：strTime的时间格式必须是yyyy-MM-dd HH:mm:ss
   * @param strTime 要转换的string类型的时间
   * @return long类型
   */
  long stringToLong(String strTime)
  ```

* date类型转换为long类型

  ```
  long dateToLong(Date date)
  ```

### 20. ToastUtils（吐司相关）

* 显示短时吐司

  ```
  showShort(@NonNull final CharSequence text)
  showShort(@StringRes final int resId)
  ```

* 显示长时吐司

  ```
  showLong(@NonNull final CharSequence text)
  showLong(@StringRes final int resId)
  ```

* 设置显示位置

  ```
  /**
   * @param gravity The gravity.
   * @param xOffset X轴偏移量（px）.
   * @param yOffset Y轴偏移量（px）.
   */
  setGravity(final int gravity, final int xOffset, final int yOffset)
  ```

* 设置消息字体颜色、大小

  ```
  //设置字体颜色
  setMsgColor(int msgColor)
  //设置字体大小
  setMsgTextSize(int textSize)
  ```

* 设置背景颜色、资源

  ```
  //设置背景颜色
  setBgColor(@ColorInt int backgroundColor)
  //设置背景资源
  setBgResource(@DrawableRes int bgResource)
  ```
  ​