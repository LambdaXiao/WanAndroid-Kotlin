# WanAndroid-Kotlin
 鸿神玩Android学习网站APP Kotlin版

#### 技术栈(MVVM + Kotlin + Coroutine + JetPack)
- Jetpack(ViewModel+LiveData+Lifecycle+DataBinding)+MVVM 架构模式
- Kotlin + 协程 + Retrofit  网络框架
- Glide 图片加载库

目前google官方也给我们提供了androidx.lifecycle:lifecycle-viewmodel-ktx的依赖包，给ViewModel中扩展了一个作用域叫viewModelScope

viewModelScope是一个绑定到当前viewModel的作用域 当ViewModel被清除时会自动取消该作用域，所以不用担心内存泄漏为问题

在Retrofit 2.6 之前，想用协程配合Retrofit来进行网络请求，我们的请求结果还要做一次转换，对此呢，我们Android界的大咖 JakeWharton还专门写了个库retrofit2-kotlin-coroutines-adapter 来做转换，有兴趣的可以看一下。

https://github.com/JakeWharton/retrofit2-kotlin-coroutines-adapter

不过，Retrofit 2.6 之后，直接对kotlin 的协程做了支持，也不需要用到这个库'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'。
