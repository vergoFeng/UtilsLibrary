![](http://i.imgur.com/e7eZQuE.png)
## About
常用工具类集合

## Gradle
Step 1. 在你的根build.gradle文件中增加JitPack仓库依赖。

    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
    }
Step 2. 在你的model的build.gradle文件中增加LUtilsLibrary依赖。

    compile 'com.github.vergoFeng:UtilsLibrary:1.0'

## 1. UtilsInit
在使用utils下的相关工具，需要进行初始化，在Application中进行初始化：

`UtilsInit.init(getApplicationContext());`

此类中提供了全局的Context，获取方法：

`UtilsInit.getContext();`

## 2. AppUtils
App相关工具类
### 2.1 功能
| 方法名 | 功能 |
| ----- |:----:|
| getAppPackageName() | 获取App包名 |
| isInstallApp(String packageName) | 判断App是否安装 |
| launchApp(String packageName) | 打开App |

## 3. DensityUtils
尺寸相关工具类
### 3.1 功能
| 方法名 | 功能 |
| ----- |:----:|
| dp2px(float dpValue) | dp转px |
| px2dp(float pxValue) | px转dp |
| sp2px(float spValue) | sp转px |
| px2sp(float pxValue) | px转sp |

## 4. EncryptUtils
加密解密相关的工具类
### 4.1 功能
* MD5加密（16进制密文字符串）

	`encryptMD5ToString(String data)`

## 5. InputMethodUtils
键盘相关工具类
### 5.1 功能
* 动态显示键盘
* 动态隐藏键盘
* 触摸空白区域隐藏键盘
### 5.2 使用
* 显示

		showSoftInput(Activity activity)
		showSoftInput(View view)

* 隐藏

		hideSoftInput(Activity activity)
		hideSoftInput(View view)

* 触摸空白区域隐藏

		/**
	     * 触摸空白区域，隐藏软键盘
	     * <p>重写dispatchTouchEvent方法<p/>
	     *
	     * @param context 上下文
	     * @param v edittext
	     * @param event 重写dispatchTouchEvent方法中的event
	     */
		touchHideSoftInput(Context context, View v, MotionEvent event)

	示例：在activity中重写dispatchTouchEvent方法，调用方法。

		@Override
	    public boolean dispatchTouchEvent(MotionEvent ev) {
	        //View v = getCurrentFocus();
			InputMethodUtils.touchHideSoftInput(this, editView, ev);
	        return super.dispatchTouchEvent(ev);
	    }

## 6. IntentUtils
Intent意图相关工具类
### 6.1 功能
* 电话相关意图
* 短信相关意图
* 邮件相关意图
* 相机相关意图，兼容了Android7.0
### 6.2 使用
* 获取跳至拨号界面意图

	`getDialIntent(String phoneNumber)`

* 获取拨打电话意图

	`getCallIntent(String phoneNumber)`

	AndroidManifest需添加权限，Android6.0以上需要动态申请权限处理

	`<uses-permission android:name="android.permission.CALL_PHONE"/>`


* 获取跳至发送短信界面的意图

		getSendSmsIntent(String phoneNumber)
		getSendSmsIntent(String phoneNumber, String content)

* 获取跳至发邮件的意图

	`getSendMailIntent(String mailAdress)`

* 获取打开相机拍照的意图

	`getCameraIntent(File file)`

	AndroidManifest需添加权限，Android6.0以上需要动态申请权限处理

		<uses-permission android:name="android.permission.CAMERA"/>
		<uses-permission android:name="android.permission.CAMERA"/>

## 7. JLog
### 7.1 功能
* 可设置Log开启和关闭
* 可设置Log全局Tag，全局Tag为空时Tag为当前类名
* 可设置Log是否显示头部信息
	* Log头部含有当前线程名
	* Log头部含有当前类及行号和函数名，支持点击跳转
* 可设置Log是否显示边框
* 支持多参数输出
* 支持JSON串的输出
* 支持XML串的输出
### 7.2 使用
#### 7.2.1 初始化
在Application的onCreate()中进行初始化

	@Override
	public void onCreate() {
	    super.onCreate();
	    JLog.Builder builder = new JLog.Builder();
	}
当然，JLog还支持多参数配置，具体如下

	new JLog.Builder()
        .setLogSwitch(BuildConfig.DEBUG) //设置log总开关，默认为true
        .setGlobalTag("fhj") //设置log全局标签，默认为null
        					 //当全局标签不为空时，我们输出的log全部为该tag，
        					 //为空时，如果传入的tag为空那就显示类名，否则显示tag
        .setLogHeadSwitch(true)	//设置log头信息开关，默认为true
        .setBorderSwitch(true)	//输出日志是否带边框开关，默认true

#### 7.2.2 使用示例
* 基础用法：`JLog.d("initView");`

	![](http://i.imgur.com/LPf8ume.png)

* 自定义tag：`JLog.d("custom", "initView");`

	![](http://i.imgur.com/fEsqiDU.png)

* 多参数输出：`JLog.d("custom", "initView", "initView1");`<br>
  注：多参数只支持自定义tag。

	![](http://i.imgur.com/d0cllk3.png)

* json输出：`JLog.json(json);` （默认在Debug日志）

	![](http://i.imgur.com/KmIQOkO.png)

	其余json相关方法：

		public static void json(String contents) {
	        log(JSON | D, sGlobalTag, contents);
	    }
	    public static void json(@TYPE int type, String contents) {
	        log(JSON | type, sGlobalTag, contents);
	    }
	    public static void json(String tag, String contents) {
	        log(JSON | D, tag, contents);
	    }
	    public static void json(@TYPE int type, String tag, String contents) {
	        log(JSON | type, tag, contents);
	    }

## 8. NetworkUtils
网络相关工具类
### 8.1 功能
| 方法名 | 功能 |
| ----- |:----:|
| isConnected()     | 判断网络是否连接 |
| isWifiConnected() | 判断wifi是否连接状态 |
| getNetworkType()  | 获取当前网络类型 |

### 8.2 使用
需要在AndroidManifest中添加权限

`<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`

## 9. PhoneUtils
手机相关工具类
### 9.1 功能
| 方法名 | 功能 |
| ----- |:----:|
| getPhoneIMEI()       | 获取手机IMEI码 |
| getSimOperatorCode() | 获取Sim卡运营商名称code值 |

### 9.2 使用
需要在AndroidManifest中添加权限

`<uses-permission android:name="android.permission.READ_PHONE_STATE"/>`

targetSdkVersion >= 23，需要动态申请改权限。

## 10. ScreenUtils
屏幕相关工具类
### 10.1 功能

| 方法名 | 功能 |
| ----- |:----:|
| getScreenWidth()     | 获取屏幕的宽度 |
| getScreenHeight()    | 获取屏幕的高度 |
| getStatusBarHeight() | 获取状态栏高度 |

### 10.2 使用

	int width = ScreenUtils.getScreenWidth();
	int height = ScreenUtils.getScreenHeight();
	JLog.d("屏幕宽度：高度" + width + "：" + height);

![](http://i.imgur.com/J6Sa9yQ.png)

## 11. SPUtils
SharedPreferences相关工具类
### 11.1 功能
* 保存数据
* 链式调用一次保存多条数据
* 读取数据
* 获取所有键值对
* 移除某个键对应的数据
* 清除所有数据

### 11.2 使用
* 获取SPUtils对象

		//默认SharedPreferences文件名，"jmportal_sp".
		SPUtils sputils = SPUtils.init();
		//自定义sharedpreferences的文件名
		SPUtils sputils = SPUtils.init("custom");

* 保存数据

		//基础使用
		SPUtils.init().putBoolean("bool", true);
		//可以使用字符串资源作为保存的键
		SPUtils.init().putBoolean(R.string.key_bool, true);
		//自动转换存储类型
		SPUtils.init().put("bool", true);

* 链式调用一次保存多条数据

		SPUtils.init()
               .putBoolean(R.string.key_bool, true)
               .putInt(R.string.key_int, 1)
               .putString(R.string.key_string, "string")
               .putLong(R.string.key_long, 1000000000)
               .putFloat(R.string.key_float, 1.1f)
               .put("put", 100)
               .putStringSet(R.string.key_set, strings);

* 读取数据

		//基础使用
		boolean booleanData = SPUtils.init().getBoolean("bool");
		//使用字符串资源作为键
		boolean booleanData = SPUtils.init().getBoolean(R.string.key_bool, defValue);
		//转换类型
		boolean booleanData = (Boolean) SPUtils.init().get("bool", true);

* 移除某个键对应的数据

		SPUtils.init().remove(bool);
		//使用字符串资源作为键
		SPUtils.init().remove(R.string.key_bool);

* 清除所有数据

	`SPUtils.init().clear();`

## 12. TimeUtils
时间相关工具类
### 12.1 功能

| 方法名 | 功能 |
| ----- |:----:|
| formatDate1(long t)  | 获取与当前时间的差 |
| formatDate2(long t)  | 日期转换格式：yyyy-MM-dd |
| formatDate3(long t)  | 日期转换格式：yyyy年MM月dd日 |
| formatDate4(long t)  | 日期转换格式：yyyy-MM-dd HH:mm:ss |
| stringToDate(String strTime) | string类型转换为date类型 |
| stringToLong(String strTime) | string类型转换为long类型 |
| dateToLong(Date date)   | date类型转换为long类型 |

### 12.2 使用
string类型转换
	
	//strTime的时间格式必须是yyyy-MM-dd HH:mm:ss
	TimeUtils.stringToDate(2017-07-11 14:22:12);
	TimeUtils.stringToLong(2017-07-11 14:22:12);
