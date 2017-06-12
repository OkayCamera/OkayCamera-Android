# OkayCamera开发原则


> 为了开发出架构合理，功能强大，代码可读可维护性高的程序，我们通过调查和目前的经验总结，提出以下开发准则，希望所有参与
项目的开发者能严格按照这些准则去开发和review。


## 关于依赖库
添加使用第三方依赖库前，请再三思考，它绝对是一个慎重的决定，我们需要考虑库的健壮性，是否活跃，是否完全符合我们的需求，
不要为了实现某一个功能而调用整个库，如果你真的需要，请将相关逻辑提出成单独的裁剪的库。
所有库的引用，需要在slack上讨论。

我们始终保持最新的依赖库，除非在最新的依赖库发现了bug，请先尝试着解决，并提PR，如果不能解决，再尝试降级。

## 关于版本号
对于Release分支我们需要定期发布版本，版本号的原则按照[semver](http://semver.org/lang/zh-CN/)的原则制定。

## 关于git commit comment
目前暂无要求，不要用中文就行

## 关于代码实现
1. 我们要尽量避免使用png格式的资源，考虑用 SVG（VectorDrawable）或 WebP 替换 PNG;
2. 为了项目了国际化维护和扩展，代码注释不能用中文，即使你英文很烂。
3. 调试Log请使用我们自定义的Logger，而非android.util.Log类。

## 关于测试用例
我建议也希望所有的功能类及方法一定要写UnitTest，测试会花费很多时间，一旦你被某个问题困住，你就会明白有了测试用例会
让你提高开发效率并且增加应用程序的健壮性;

## 关于构建
使用 CI (持续集成) 来构建和分发你的测试和生产环境的apk;

## 关于发布
使用 [playstore](https://github.com/Triple-T/gradle-play-publisher) 来自动化你的发布过程;



## 不要做
1. 如果用户看不见有些界面, 请一定不要绘制它!;
2. 除非真的需要，否则不要使用数据库;
3. 在程序中请勿使用AsyncTask，请使用RxJava(https://github.com/ReactiveX/RxJava)代替，因为RxJava 是对 AsyncTask 和其它异步任务类最好的替代品;
4. 涉及到网络请求，请使用Retrofit，它是目前 android 最好的处理网络事务的依赖库；
5. 使用 Retrolambda 来精简项目代码;
6. EventBus非常好用, 但在使用EventBus时，要注意不要使代码库变得更混乱;
7. 请按照事务编程，把每一个事务都从应用程序主线程移除;
8. 如果你会使用依赖注入，请大量使用它吧。


> 其他注意事项可参考：https://juejin.im/entry/58217b84570c350060bc40f8?utm_source=gold-miner&utm_medium=readme&utm_campaign=github


后期在Slack中讨论的结果也会加入到该准则中。