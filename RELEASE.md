<!--
# (TBD next version)
## New features
## Deprecated or removed
## Other changes
-->

# 0.1.10 (TBD)
## New features
+ use JetBrains Java Annotations
+ add *addStylesheet(String)* method
+ add *StageUtils#showNonResizableStageAndWait(URL, String, Window, Consumer<T>)*
+ add *ReadOnlyStringConverter* and *SimpleStringConverter* class
## Deprecated or removed
- method *StageUtils#showNonResizableStageAndWait(URL, String, Window, Consumer<T>, Consumer<T>)* is deprecated
## Other changes
* Update build plugins and dependencies versions
* Minor update javadocs

# 0.1.9
## New features
+ add *FXUtils#updateSelectionMode(SelectionMode, Control...)* method
+ show utility window support modality *StageUtils*
## Deprecated or removed
- removed deprecated API from *FXUtils*
- removed usage *@Nullable* an some *@Nonnull* annotation
- mark *@Nonnull* annotation as deprecated
## Other changes
* All arguments can be null by default, unless otherwise stated (except @Nonnull annotation)
* Update javadocs
* Update dependency versions
* Fix *StageBuilder*
* Fix *ObjectTextFieldTableCell*
* Fix Code Smells
* Cleanup _datepicker_ package

# 0.1.8
## New features
+ function to change [selection mode](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/SelectionMode.html) used in selection model for multiple
  [ListView](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/ListView.html),
  [TreeView](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/TreeView.html),
  [TableView](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/TableView.html)
  (javadocs: FXUtils#updateSelectionMode)
+ browse url in default browsers with exception consumer (javadocs: *FXUtils#browse(String, Consumer)*)
+ alert builder (*AlertBuilder* class)
+ show alert with html content (*WebDialog* class)
+ add *@Experimental* annotation to mark non-stable functionality

## Deprecated or removed
- internal *Nullable* annotation is deprecated and will be removed in 0.1.11

## Other changes
* Compatibility with Java 11
* remove google jsr305 library, create internal Nonnull, Nullable annotations
* *DialogFasade#showDialog* support [Alert.AlertType.NONE](https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/control/Alert.AlertType.html#NONE)
* Add `RELEASE.md`
* Minor update javadocs
* Add missing _final_ keywords
* Fix code header
* Fix some code smells, javadocs
* Add jacoco and javadocs maven plugin
* Update junit and mockito versions
* Other small changes

# Before 0.1.8
Only internal versions
