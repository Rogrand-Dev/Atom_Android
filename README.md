## 概述

**Atom for Android** 是一个是基于 Material Design、MVP、RxJava2、Retrofit2、Dagger2 等主流开源项目的快速开发框架。

## 功能

- 使用 MVP 架构整个项目；
- 使用 RxJava2 配合 Retrofit2 进行网络请求；
- 对网络请求结果进行预处理操作；
- 使用 Dagger2 将 M 层注入 P 层，将 P 层注入 V 层，无需 new ，直接调用对象；
- 使用 OkHttp3 对网络返回内容做缓存，配置日志、超时重连、头部消息；
- 使用 Glide 做图片的处理和加载；
- 使用 Fragmentation 简化 Fragment 的操作和懒加载；
- 使用 RecyclerView 实现下拉刷新、上拉加载；
- 底部导航
- 登录注册、个人中心、个人信息、设置、关于 UI；
- 自定义 Button、Toast、Dialog;
- 多种 Banner 样式、动画；
- 动态权限配置；

## 主流开源项目

### UI
- [Material Dialogs](http://write.blog.csdn.net/mdeditor)：Material Design 风格的 Dialog
- [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)：高效的使用 RecyclerView 应对项目中的常见需求的 Adapter
- [Glide](https://github.com/bumptech/glide)：图片加载
### Rx系列
- [RxJava](https://github.com/ReactiveX/RxJava)：响应式编程框架
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
### 网络相关
- [Retrofit](https://github.com/square/retrofit)
- [OkHttp](https://github.com/square/okhttp)
- [Gson](https://github.com/google/gson)：Google 提供的 json 解析库
- [Stetho](https://github.com/facebook/stetho)：调试工具
### DI
- [Dagger2](https://github.com/google/dagger)：快速依赖注入器
- [ButterKnife](https://github.com/JakeWharton/butterknife)：注入框架
### DB
- [Realm](https://github.com/realm/realm-java)：数据存储库

### 其他
- [EventBus](https://github.com/greenrobot/EventBus)：事件管理总线
- [Logger](https://github.com/orhanobut/logger)：打印日志库
- [EasyPermissions](https://github.com/googlesamples/easypermissions)：动态权限

## 扩展阅读
- [Android 开发规范](http://www.jianshu.com/p/59c0b0e38231)：来自简书的 Android 开发规范
- [Android 系统权限](https://developer.android.google.cn/guide/topics/security/permissions.html)：Android 官方的权限介绍
