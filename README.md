# bignews
# andrid diff 合成差异包
## 集成 项目根目录中 build.gradle 增加
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
## 然后在你的使用的module中的 build.gradle 增加引用
dependencies {
	        implementation 'com.github.zouxianbincc:bignews:v1.0.1'
	}
