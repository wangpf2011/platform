Espresso——功能方法介绍

1、通过方法onView()可以获取某个控件这个对象，即ViewInteraction。

withText：通过文本来获取对象
例如： ViewInteraction vi_text = onView(ViewMatchers.withText("通过该文本")) ;

withId：通过id来获取对象
例如：ViewInteraction vi_id = onView(ViewMatchers.withId(R.id.id2)) ;

all：通过多个条件来获取对象，不如说ID或者text有重复的时候，可以使用该方法
例如：onView(allOf(withId(R.id.button_signin), withText("Sign-in"))) ; //通过id和text来获取对象
onView(allOf(withId(R.id.button_signin), not(withText("Sign-out")))); //通过id，且text不为Sign-out来获取对象

2、对于ViewInteraction控件的操作，主要通过perform()来实现。
例如：
ViewInteraction helloWorldText = onView(ViewMatchers.withId(R.id.bt1));
helloWorldText.perform(ViewActions.click());

ViewActions.click(): Clicks on the view.  //点击该控件
ViewActions.typeText(): Clicks on a view and enters a specified string. //在该控件内输入内容
ViewActions.scrollTo(): Scrolls to the view. The target view must be subclassed from ScrollView and the value of its android:visibility property must be VISIBLE. For views that extend AdapterView (for example, ListView), the onData() method takes care of scrolling for you.  //滑动到控件
ViewActions.pressKey(): Performs a key press using a specified keycode. //
ViewActions.clearText(): Clears the text in the target view. //清除控件内的文本

3、对控件的验证方法主要有以下方式：
例如：
匹配通过文本来获取的对象与通过Id来获取的对象
onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));

检测该控件是否显示
onView(withId(R.id.textToBeChanged)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

doesNotExist:Asserts that there is no view matching the specified criteria in the current view hierarchy.
matches: Asserts that the specified view exists in the current view hierarchy and its state matches some given Hamcrest matcher.
selectedDescendentsMatch : Asserts that the specified children views for a parent view exist, and their state matches some given Hamcrest matcher.
