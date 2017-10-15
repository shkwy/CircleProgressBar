# CircleProgressBar
Custom circle ProgressBar

1.添加依赖

 在项目的grade文件中添加:
 
    allprojects {
	    repositories {
	        maven { url 'https://jitpack.io' }
	    }
	}

在moudle的grade文件中添加:

	dependencies {
        compile 'com.github.shkwy:CircleProgressBar:1.0'
    }
    
2.使用
   
    <com.sky.library.CircleProgressBar
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
          
3.可以使用自定义属性设置:
    
        app:bollColor="@color/colorAccent" // 进度条的颜色
        app:needBackground="true" // 是否需要背景
        app:needDifferentColor="true" // 是否需要每个小球的颜色不同
        
        当app:needDifferentColor="true"的值为true的时候还支持自定义每个球的颜色
        app:color_0="@color/colorAccent"
        app:color_1="@color/colorAccent"
        app:color_2="@color/colorAccent"
        app:color_3="@color/colorAccent"
        app:color_4="@color/colorAccent"
        app:color_5="@color/colorAccent"
        app:color_6="@color/colorAccent"
        app:color_7="@color/colorAccent"
        
4.相关属性也可以在代码中动态设置
