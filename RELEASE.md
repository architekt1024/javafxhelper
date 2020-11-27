# (TBD next version)
## New features
## Deprecated or removed
## Other changes


# 0.1.9 (SNAPSHOT yet)
## New features
## Deprecated or removed
- removed deprecated API from FXUtils
- removed usage *@Nullable* annotation
## Other changes
* All arguments can be null by default


# 0.1.8
## New features
+ function to change [selection mode](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/SelectionMode.html) used in selection model for multiple
  [ListView](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/ListView.html),
  [TreeView](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/TreeView.html),
  [TableView](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/TableView.html)
  (javadocs: FXUtils#updateSelectionMode)
+ browse url in default browsers with exception consumer (javadocs: FXUtils#browse(String, Consumer))
+ alert builder (AlertBuilder class)
+ show alert with html content (WebDialog class)
+ add *@Experimental* annotation to mark non-stable functionality

## Deprecated or removed
- internal Nullable annotation is deprecated and will be removed in 0.1.11

## Other changes
* Compatibility with Java 11
* remove google jsr305 library, create internal Nonnull, Nullable annotations
* DialogFasade#showDialog support [Alert.AlertType.NONE](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/Alert.AlertType.html#NONE)
* Add RELEASE.md
* Minor update javadocs
* Add missing _final_ keywords
* Fix code header
* Fix some code smells, javadocs
* Add jacoco and javadocs maven plugin
* Update junit and mockito versions
* Other small changes

# Before 0.1.8
Only internal versions
