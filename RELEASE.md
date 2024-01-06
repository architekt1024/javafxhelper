<!--
# (TBD next version)
## New features
## Deprecated or removed
## Other changes
-->

# 0.1.11 (TBD)
## New features
+ add resources param to *StageBuilder*
+ new method *StageUtils#loadMainStage* without stylesheet
+ methods in *FileDialog* return *Optional* (only in dialog sub-package)
+ add modality in *AlertBuilder*
+ add *DesktopUtils* in *utils* subpackage
## Deprecated or removed
- *WebUtils* is deprecated. Moved to *utils* subpackage
- *FileDialog* is deprecated. Moved to *dialog* subpackage
- internal *@Nullable* annotation is removed
- *FXUtils#runFile* is moved to *utils.DesktopUtils#openFile*
- *FXUtils#browse* is moved to *utils.DesktopUtils#browse*
- *PropertiesUtils* is moved to *utils* subpackage
## Other changes
* rename *EntryMapTableRecord* to *PairTableRecord*
* update slf4j to 2.0.7 and other plugins versions
* bugfix *FileDialog#showOpenFileDialog* methods throw NullPointerException because *extensionFilters* is null
* bugfix parent window not work for *AlertBuilder*
* bugfix cannot close alert created by *AlertBuilder*
* code style fix and other minor bugfixes
* add missing annotations

# 0.1.10
## New features
+ use JetBrains Java Annotations
+ new *StageBuilder#addStylesheet(String)* method
+ new *StageUtils#showNonResizableStageAndWait(URL, String, Window, Consumer<T>)*
+ new *ReadOnlyStringConverter* and *SimpleStringConverter* class
+ new *DialogFacade#showTextInputDialog(String title, String headerText, Window parentWindow)*
+ new *DialogFacade#showTextInputDialog(String title, String contentText, String headerText, Window parentWindow)*
+ new *PropertiesUtils* class
+ new *DialogService* class similar to *DialogFacade*, but you need to instantiate and provide parent window and title
+ new *EntryMapTableRecord* experimental class
## Deprecated or removed
- method *StageUtils#showNonResizableStageAndWait(URL, String, Window, Consumer<T>, Consumer<T>)* is deprecated
- experimental *WebDialog* class
- *DialogFacade#createAlert* is deprecated
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
* Cleanup *datepicker* package

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
