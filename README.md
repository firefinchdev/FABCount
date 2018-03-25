# FABCount
A better Floating Action Button with a counter badge (at any corner and of any size)

## Screenshots

<img src="https://raw.githubusercontent.com/firefinchdev/FABCount/master/pics/1.png" width="200"><img src="https://raw.githubusercontent.com/firefinchdev/FABCount/master/pics/2.png" width="200"><img src="https://raw.githubusercontent.com/firefinchdev/FABCount/master/pics/3.png" width="200"><img src="https://raw.githubusercontent.com/firefinchdev/FABCount/master/pics/4.png" width="200"><img src="https://raw.githubusercontent.com/firefinchdev/FABCount/master/pics/5.png" width="200"><img src="https://raw.githubusercontent.com/firefinchdev/FABCount/master/pics/6.png" width="200"><img src="https://raw.githubusercontent.com/firefinchdev/FABCount/master/pics/7.png" width="200">

## Installation

Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Include the library in your `build.gradle`

```groovy
dependencies {
    compile 'com.github.firefinchdev:FABCount:c24af3415d'
}
```

For Maven,

```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>

<dependency>
	<groupId>com.github.firefinchdev</groupId>
	<artifactId>FABCount</artifactId>
	<version>c24af3415d</version>
</dependency>
```

## Usage & Customization

Directions Available:
```
TOP_LEFT | TOP_RIGHT | BOTTOM_LEFT | BOTTOM_RIGHT
```

```xml
<com.techno.fabcount.FABCount
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_add_white_24dp" />
```

and programmatically you can use one of these methods:

```java
FABCount fabCount = findViewById(R.id.fab);
fabCount.setCount(10); 			// Set the count value to show on badge
fabCount.increase(); 			// Increase count by 1
fabCount.decrease(); 			// Decrease count by 1
fabCount.changeTextSize(26);	// Set the counter text size
fabCount.changeTextSize( fabCount.getCurrentSize() + 1);

fabCount.setDirection(FABCount.TOP_LEFT);		// Set counter badge position
fabCount.setDirection(FABCount.TOP_RIGHT);
fabCount.setDirection(FABCount.BOTTOM_LEFT);
fabCount.setDirection(FABCount.BOTTOM_RIGHT);

int dir = fabCount.getDirection();		// Get counter badge direction
int size = fabCount.getCurrentSize();	// Get counter badge text size

```


The recommended way to customize the background color is by using `app:backgroundTint` 

```xml
<com.techno.fabcount.FABCount
        android:id="@+id/counter_fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:backgroundTint="#009688"
        android:src="@drawable/ic_add_white_24dp" />
```
