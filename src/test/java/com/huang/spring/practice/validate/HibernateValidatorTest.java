package com.huang.spring.practice.validate;

import com.huang.spring.practice.BaseTest;
import com.huang.spring.practice.param.valiadte.vo.*;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author  yellowstar
 * @version 1.0
 * @description
 * @created 2021/5/28
 */
public class HibernateValidatorTest extends BaseTest {

    // 校验Java Bean
    private static Validator validator;

    // 校验method。static method貌似校验不了
    private static ExecutableValidator executableValidator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        executableValidator = validator.forExecutables();
    }



    /**
     * 校验对象的field、properties
     */
    @Test
    public void testJavaBeanValidation() {
        Car car = new Car(null, "DD-AB-123", 1);

        Set<ConstraintViolation<Car>> constraintViolationExceptions = validator.validate(car);

        assertEquals(2, constraintViolationExceptions.size());

        return;

    }

    /**
     * cascaded validation
     * 对象嵌套校验不行？自定义校验注解是肯定可以实现的
     * 👇 fixed
     * 需要@Valid注解
     */
    @Test
    public void testJavaBeanNestedValidation() {
        Car car = new Car("BMW", "DD-AB-123", 4);
        car.addDriver(new Driver(""));

        Set<ConstraintViolation<Car>> constraintViolationExceptions = validator.validate(car);

        assertEquals(1, constraintViolationExceptions.size());
        return;
    }

    /**
     * 对象嵌套校验，循环依赖
     */
    @Test
    public void testCircularDependencyValidation() {
        A a = new A();
        B b = new B();

        a.setB(b);
        b.setA(a);

        Set<ConstraintViolation<Object>> validate = validator.validate(a);

        return;
    }

    /**
     * 使用注解校验容器里的值，支持的容器有：
     *      1.Set、List、Map、java.util.Optional
     *      2.自定义容器(类似于ValueHolder的感觉)，不过自定义容器需要实现ValueExtractor接口
     */
    @Test
    public void testContainerValidation() {
        Car car = new Car("BMW", "DD-AB-123", 4);
        car.addPart("");

        Set<ConstraintViolation<Car>> constraintViolationExceptions = validator.validate(car);

        assertEquals(1, constraintViolationExceptions.size());
        ConstraintViolation<Car> next = constraintViolationExceptions.iterator().next();
        assertEquals("不能为空", next.getMessage());
        return;

    }

    /**
     *  当容器里的值是我们自定义创建的对象，不使用自定义校验注解，无法校验对象里已经配置的要校验的值？
     *  👇 fixed
     * 需要@Valid注解
     */
    @Test
    public void testContainerNestedValidation() {
        Map<String, List<Car>> salerAndSaledCarMap = new HashMap<>();
        salerAndSaledCarMap.put("销售员1号", Arrays.asList(
                new Car("BMW", "DD-AB-123", 1),
                new Car("TOYOTA", "DD-AB-123", 1)));

        CarSaledRecord carSaledRecord = new CarSaledRecord(salerAndSaledCarMap);

        Set<ConstraintViolation<CarSaledRecord>> validate = validator.validate(carSaledRecord);

        assertEquals(2, validate.size());

        return;
    }

    /**
     * 在类上面，使用自定义注解，约束多个field之间的关系
     */
    @Test
    public void testClassLevelValidation() { }

    /**
     * 约束继承（父类，接口）
     */
    @Test
    public void testConstraintInheritance() { }

    /**
     * 约束不能作用域静态方法上
     */
    @Test
    public void testStaticMethodValidation() { }

    /**
     * 方法重写时，方法参数的约束不能打破李氏替换原则
     */
    @Test
    public void testMethodParamConstraintsInInheritance() { }

    /**
     * 方法重写时，方法返回值的约束可以增强
     */
    @Test
    public void testMethodReturnValConstraintsInInheritance() { }

    /**
     * 测试@ELAssert
     */
    @Test
    public void testELAssert() { }

    /**
     * 测试@ParameterScriptAssert
     * 支持任何 JSR 223 compatible ("Scripting for the JavaTM Platform") scripting language
     *
     *     @ParameterScriptAssert(lang = "javascript", script = "luggage.size() <= passengers.size() * 2")
     *     public void load(List<Person> passengers, List<PieceOfLuggage> luggage) {
     *         //...
     *     }
     */
    @Test
    public void testParameterScriptAssert() { }

    /**
     * 测试校验不通过时的提示语生成
     *     @NotNull(message = "${errMsg} !")
     *     errMsg配置在resource bundle中，ValidationMessages.properties
     *     也可以国际化：ValidationMessages_en_US.properties，使用JVM默认的Locale#getDefault()
     */
    public void testInterpolatingConstraintErrorMsg() { }

    /**
     * 测试msg表达式(默认的消息插值算法 and "resource bundle")：{被校验的参数}，${实际被校验的数值}，${EL expression}
     */
    public void testInterpolationWithMsgExpression() {

    }

    /**
     * 测试自定义消息的ResourceBundle，要实现ResourceBundleLocator接口，可以实现从数据库或者配置中心获取resouce bundle。
     * hibernate-validator默认提供几种开箱即用的ResourceBundleLocator实现：PlatformResourceBundleLocator、AggregateResourceBundleLocator
     */
    @Test
    public void testCustomMessageInterpolation() {

    }
}
