通过封装GradientDrawable、StateListDrawable 、LayerDrawable 代替每次都需要创建一个shape.xml的不便，一定程度上也可以减少apk体积，并且使用简单。

## 功能
* 不用再写shape.xml文件了！！！
* 链式调用
* 涵盖Shape几乎常用的所有属性，如：TYPE，Radius，Stroke，Solid，Gradient，GradientType，GradientCenter，GradientRadius，size
* 支持Selector
* 支持Layer-list

## How to：
**Step 1. Add the JitPack repository to your build file** 
Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency

```
dependencies {
	        compile 'com.github.sdfdzx:SupperShape:v1.0.2'
	}
```
![效果](https://github.com/sdfdzx/SupperShape/blob/master/demo.gif)

## 使用
### 一、ShapeBuilder代替shape。
#### Demo
```
ShapeBuilder.create()
            .Type(RECTANGLE)
            .Solid(Color.RED)
            .Stroke(5,Color.BLACK)
            .build(View);
```
#### Use
设置对应的属性，调用build(View)传入需要设置背景的view即可。获得构建的drawable可以调用该build()方法返回。
#### Api
1.type形状属性
type:RECTANGLE,OVAL,LINE,RING
```
Type(int type)
```
2.Stroke边框属性
```
    /**
     * 边线
     * @param px -width,需要px值
     * @param color -color值
     * @param dashWidth -dashWidth 横线的宽度
     * @param dashGap -dashGap 点与点间的距离
     */
public ShapeBuilder Stroke(int px, int color) 
public ShapeBuilder Stroke(int px, int color, int dashWidth, int dashGap)
```
3.Solid填充属性
```
    /**
     *
     * @param color -背景颜色
     */
    public ShapeBuilder Solid(int color)
```
4.Radius圆角属性
```
    /**
     *
     * @param px -圆角，四个角保持一致
     */
    public ShapeBuilder Radius(float px)
    /*
     * 圆角
     * @param topleft 左上
     * @param topright 右上
     * @param botleft 左下
     * @param botright 右下
     */
    public ShapeBuilder Radius(float topleft, float topright, float botleft, float botright)
```
5.Gradient渐变属性
```
    /*
     * 渐变，默认的Linear渐变
     * @param startColor 开始颜色
     * @param centerColor 中心颜色
     * @param endColor 结束颜色
     */
    public ShapeBuilder Gradient(int startColor, int centerColor, int endColor)
	// @param angle 角度，需要是45的整数倍
	public ShapeBuilder Gradient(int angle, int startColor, int centerColor, int endColor)
    /*
     * 渐变，设置渐变方向
     * @param orientation 方向支持类型
     *                    0-LEFT_RIGHT
     *                    45-BL_TR
     *                    90-BOTTOM_TOP
     *                    135-BR_TL
     *                    180-RIGHT_LEFT
     *                    225-TR_BL
     *                    270-TOP_BOTTOM
     *                    315-TL_BR
     */
    public ShapeBuilder Gradient(GradientDrawable.Orientation orientation, int startColor, int
            centerColor, int endColor)
```
6.GradientType渐变类型属性
```
    /*
     * 渐变type
     * @param type linear (default.)-LINEAR_GRADIENT
     *             circular-RADIAL_GRADIENT
     *             sweep-SWEEP_GRADIENT
     * @return
     */
    public ShapeBuilder GradientType(int type)
```
7.GradientCenter渐变中心属性
```
    /*
     *  这两个属性只有在type不为linear情况下起作用。
     * @param x 相对X的渐变位置
     * @param y 相对Y的渐变位置
     * @return
     */
    public ShapeBuilder GradientCenter(float x, float y)
```
8.GradientRadius渐变颜色半径
```
    /*
     * 该属性只有在type="radial"有效
     * @param radius 渐变颜色的半径
     * @return
     */
    public ShapeBuilder GradientRadius(float radius)
```
9.Size属性
```
    /*
     * 设置size
     * @param width 宽
     * @param height 高
     * @return
     */
    public ShapeBuilder setSize(int width, int height)
```
### 二、ShapeListBuilder替代Selector
#### Demo
```
        ShapeBuilder builder1 = ShapeBuilder.create()
                .Type(RECTANGLE)
                .Solid(Color.RED);
        ShapeBuilder builder2 = ShapeBuilder.create()
                .Type(RECTANGLE)
                .Solid(Color.RED);

        ShapeListBuilder.create(builder1.build())
                .addShape(builder2.Solid(Color.BLUE).build(), android.R.attr.state_selected)
                .build(findViewById(R.id.tv1));

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.tv1).setSelected(!findViewById(R.id.tv1).isSelected());
            }
        });
```
#### Use
1.传入默认状态的drawable
```
    /*
     * @param drawable 传入默认状态下的drawable
     */
    public static ShapeListBuilder create(Drawable drawable)
```
2.添加对应状态的drawable
```
    /*
     * 添加状态
     * @param shape 状态对应的shape
     * @param state 状态类型
     */
    public ShapeListBuilder addShape(Drawable shape, int... state)
```
**（这里要注意添加的顺序，只要有一个状态与之相配，背景就会被换掉。所以不要把大范围放在前面了，会造成没有什么效果了。）**  
3.build(View)即可
### 三、LayerBuilder替代Layer-list
#### Demo
```
LayerBuilder.create(builder1.build(), builder2.build()).Bottom(1, 15).build(findViewById(R
                .id.tv3));
```
#### Use
1.传入多个drawable
```
public static LayerBuilder create(Drawable... drawables)
```
2.设置index的偏移量
```
public LayerBuilder Left(int index, int px)
public LayerBuilder Top(int index, int px)
public LayerBuilder Right(int index, int px)
public LayerBuilder Bottom(int index, int px)
public LayerBuilder setInset(int index,int left,int top,int right,int bottom)
```
3.build(View)即可
