# 关于测试

这个项目使用了两种测试的方式：单元测试和设备化测试

下图是关于Android应用的测试手段的示意：
![Android应用的测试手段](http://img.blog.csdn.net/20160924155341384?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center)
![](https://developer.android.com/images/testing/test-types_2x.png)

具体的测试case的编写要求及规范如下：

## Local unit tests
单元测试代码位于：`app/src/test/java/`, 这些测试运行与本地java虚拟机，不能包含任何Android Framwork API的函数功能，

关于如何编写测试案例，请查看谷歌官方文档：[Building Local Unit Tests](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html)

## 设备化单元测试（Instrumented tests）
Instrumented tests 测试代码位于`app/src/androidTest/java/`目录下，这些测试case运行于Android硬件或虚拟设备上，
设备化测试被构建进了Apk，并与APK一起进行测试。测试代码和APK跑在同一个进程。因此，测试代码中可以调用app的方法和成员变量，
与你的app进行自动化用户交互。

关于如何创建设备化测试，请查看谷歌官方文档：
- [Building Instrumented Unit Tests](https://developer.android.com/training/testing/unit-testing/instrumented-unit-tests.html)
- [Automating User Interface Tests](https://developer.android.com/training/testing/ui-testing/index.html)
- [Testing App Component Integrations](https://developer.android.com/training/testing/integration-testing/index.html)

