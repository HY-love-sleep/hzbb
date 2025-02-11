# 创建型

## 一、工厂方法模式

工厂方法模式提供创建对象的接口，将实际创建对象的过程推迟到子类中进行执行。这样客户端就不需要知道创建对象的具体细节、流程， 直接调用工厂方法来创建对象就行，使得客户端与具体的对象创建代码解耦，提高灵活性和可维护性；

工厂方法模式一般有一个抽象的工厂类，定义创建对象的抽象方法，具体的对象创建则由子类来实现；

优点：

- 松耦合
- 扩展性
- 封装性

缺点：

- 引入额外的复杂性，需要定义不同的工厂类和产品类；



应用：

1. 数据库连接池：根据不同的数据库类型，创建不同的数据库连接池；



## 二、抽象工厂方法模式

抽象工厂提供一个接口， 用于创建**一系列**相关或者相互依赖的对象【不同于工厂方法模式中工厂接口用于创建**一个**对象】，抽象工厂方法模式有四个定义：

1. 抽象工厂：声明了一组创建不同产品的抽象方法；
2. 具体工厂：实现了抽象工厂的接口，负责创建某种特定种类的产品对象；
3. 抽象产品：定义了产品的通用接口，具体产品必须实现这个接口；
4. 具体产品：实现抽象产品的接口，是抽象工厂创建的实际对象；



优点：

- 松耦合：客户端代码通过工厂接口创建产品；
- 一致性：保证产品族里产品的一致性；
- 扩展性：扩展时添加具体工厂和具体产品类就行；

缺点：

- 引入额外的复杂性；



应用：

1. 操作系统图形界面：不同的操作系统的图形界面包括窗口、按钮、文本框等，抽象工厂可以创建一组相关的UI控件；



## 三、建造者模式

为了解决复杂对象的创建问题，建造者提供一种将一个复杂对象的构建过程与其表示分离的方法，他将对象的创建过程封装在一个独立的建造者类中，每次通过该类来构建对象，这样就能够通过不同的建造者来构建不同的对象。建造者模式通常有以下几个对象：

- 产品：表示正在构建的复杂对象；
- 抽象建造者：定义构建产品的步骤和方法；
- 具体建造者：实现抽象建造者，完成产品的构建过程；
- 指导者：负责控制建造的过程；



## 四、原型模式

原型模式可以通过复制现有对象来创建新对象， 而不是从头开始构建。但原型模式也有一些限制：

- 深克隆问题：原型模式默认进行浅克隆，会复制对象本身及其引用，如果对象内部包含其他对象，需要实现深克隆来复制整个对象结构。
- 克隆方法的实现：有些对象可能不容易进行克隆，特别是实际到文件、网络连接等资源的情况；



## 五、单例模式

单例模式确保一个类只能创建一个实例对象，提供一个全局访问点，常用于线程池、bean等；

- 优点：节省了资源和内存；
- 缺点：过度使用单例模式可能导致全局状态的难以控制，以及模块之间的紧耦合；



## 六、适配器模式

适配器模式能够组合两个不兼容的接口（即类或者对象）一起工作。适配器模式通过引入一个适配器类来充当中间人，将一个接口转换为另一个接口；

适配器类包含一个不兼容接口的引用，并实现了期望的目标接口；提高了代码的可重用性；



## 七、桥接模式

桥接模式通过将抽象部分与具体部分分离，使他们能够独立的进行变化。在桥接模式中，通过创建一个桥接接口（或者抽象类），其中包含一个指向具体实现的引用，将抽象部分和具体部分连接起来。

这样抽象部分和具体部分能够独立扩展，互不影响，避免了类层次结构的爆炸性增长，也就是常说的 “**组合优于继承！**”

例如数据库驱动需要连接不同类型的数据库，桥接模式能够使得数据库驱动程序的抽象部分与不同的数据库分离，使得应用程序可以在不同的数据库上运行而无需修改驱动程序。



## 八、组合模式

组合模式将对象组合成树状结构，其中树的节点可以是单个对象或者对象组合。这样无论是操作单个对象还是对象的组合，都能用统一的方式进行操作。

组合模式通过定义一个共同的抽象类或者接口来表示单个对象和对象的组合，从而实现透明的处理；

1. 优点：
   - **透明性：** 使用组合模式，客户端可以一致地对待单个对象和对象组合，无需关心具体对象的类型。
   - **简化客户端代码：** 客户端不需要判断操作的对象是单个对象还是对象组合，从而简化了客户端的代码。
   - **灵活性：** 可以很方便地添加新的叶子或复合对象，扩展性较好。
2. 缺点：
   - **不适合所有情况：** 并非所有情况都适合使用组合模式。在一些情况下，可能会引入不必要的复杂性。
   - **可能限制操作：** 组合模式可能会限制某些特定对象的操作，因为共同的抽象接口可能无法涵盖所有可能的操作；



## 九、装饰器模式

装饰器模式能够在修改对象结构的情况下，动态的添加功能。【继承虽然也能做到， 但是可能导致类爆炸的问题】；

通过创建一个装饰类来包装原始类， **装饰类和原始类实现相同的接口，装饰类内部持有原始对象的引用**，并且可以根据需求包装额外的功能。

装饰类使得功能的增加和修改更加灵活，不影响其他部分的代码，但装饰器模式也会增加一些小的类，总而增加代码的复杂性；



## 十、外观模式

外观模式通过引入一个外观类（Facade），将复杂的子系统接口进行封装，为客户端提供一个简单的高层接口。外观类充当了客户端与子系统之间的中间人，处理客户端的请求并将其转发给适当的子系统。外观模式并不在系统中添加新功能，它只是提供了一个更简洁的接口，以简化客户端的操作。
