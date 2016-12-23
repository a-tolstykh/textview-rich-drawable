# TextViewRichDrawable

This is a tiny library which empowers TextView's compound drawables with size specifying and vector support.

This library is just an extension of Android's TextView.

## Usage

* In XML layout: 

```xml
    <com.tolstykh.textviewrichdrawable.TextViewRichDrawable
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Some text"
        app:compoundDrawableHeight="24dp"
        app:compoundDrawableWidth="24dp"
        app:drawableTopVector="@drawable/some_vector_drawble"
        app:drawableEndVector="@drawable/another_vector_drawable" />
```        

* All customizable attributes:

```xml
    <declare-styleable name="TextViewRichDrawable">
        <attr name="compoundDrawableWidth" format="dimension"/>
        <attr name="compoundDrawableHeight" format="dimension"/>
        <attr name="drawableStartVector" format="reference" />
        <attr name="drawableTopVector" format="reference" />
        <attr name="drawableEndVector" format="reference" />
        <attr name="drawableBottomVector" format="reference" />
    </declare-styleable>
```

## Example

![TextView-rich-drawable](demo_screenshot.png)

## Download

```groovy
repositories {
    maven {
        url 'https://dl.bintray.com/tolstykh/rich-compound-drawable/'
    }
}
```

```groovy
compile 'com.tolstykh.textviewrichdrawable:textview-rich-drawable:0.1.0'
```
