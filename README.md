##注意事项

[解决ImageLoader加载HTTPS图片证书校验异常问题](http://www.cnblogs.com/csdnLion2016/p/5720590.html)


工程build.gradle 添加

```
allprojects {
    repositories {
        jcenter()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}
```
