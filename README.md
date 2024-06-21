<h1>JPA-Quick start</h1>

<h3>1. JDBC API</h3>

<p> <code>JDBC(Java Database Connectivity)</code>는 자바에서 가장 오래된 데이터베이스 연동 기술입니다. 모든 관계형 데이터 베이스에 대해서 동일한 자바 코드를 사용합니다. <code>JDBC</code>는 <code>다형성(Polymorphism)</code>을 기반으로 동작하기 때문에 데이터베이스와 무관하게 동일한 자바 코드를 사용할 수 있습니다.</p>

<h3>2. JDBC의 문제</h3>

<p> 커넥션을 연결하고 종료하는 중복되는 코드를 별도의 메소드(<code>getConnection</code>, <code>close</code>)로 분리시켰음에도 불구하고 여전히 모든 데이터베이스 연동 메서드(<code>insertEmployee</code>, <code>getEmployeeList</code>)에 "반복되는 코드"가 등장합니다.</p>
<p>또한,<code>SQL</code> 구문들이 <code>DAO</code> 클래스에 포함되어 있기 때문에, <code>SQL</code> 명령어를 수정하기 위해서는 <code>SQL</code>이 선언된 <code>DAO</code> 클래스를 찾아야 합니다. 수정한 이후에는 다시 컴파일을 해야 합니다. 이런 <code>JDBC</code>의 문제를 해결하기 위해 마이바티스와 같은 <code>"SQL 매퍼 프레임워크"</code>가 등장하였습니다.</p>

<h3>3. MyBatis 프레임워크</h3>

<p> <code>마이바티스(MyBatis) 프레임워크</code>는 아파치에서 만든 <code>아이바티스(iBATIS)</code> 프레임워크에서 파생된 <code>SQL 매퍼 프레임워크</code>입니다.</p>
<p><code>마이바티스</code>는 <code>JDBC</code>로 데이터베이스를 연동하기 위해 드라이버 로딩, 커넥션 연결, 해제까지 복잡하고 반복적인 작업을 프레임워크가 대신 처리해주기 때문에 개발자는 비즈니스 로직에 집중할 수 있게됩니다.</p>
<p><code>마이바티스</code>는 자바 코드와 SQL을 분리합니다. <code>SQL 매퍼</code>라는 <code>XML</code>파일을 만들어서 <code>DAO</code> 클래스에서 사용할 <code>SQL</code>을 저장하고 관리합니다.</p>
<p> ➜ <code>SQL</code> 명령어를 자바 코드와 분리하여 한 곳에 모아서 관리하기 때문에 <code>SQL</code>을 검색하기도 쉽고, <code>SQL</code>을 수정했을 때 자바 소스를 다시 컴파일 하지 않아도 됩니다.</p>
<p>마이바티스에서는 <code>XML</code>파일에 분리된 <code>SQL</code>을 실행하기 위한 자바코드는 한 줄에 불과하기 때문에 데이터베이스 연동이 매우 간결해졌습니다.</p>

<h3>MyBatis의 문제</h3>
<p> <code>MyBatis</code>에서 직접 <code>SQL-Mapper</code>를 작성할 때 발생하는 오류를 정확하게 잡기 어려워서 오타와 같은 부분에 대하여 많은 어려움이 있었습니다. <code>MyBatis</code>와 같은 <code>Data-Mapper</code>는 자바코드와 SQL을 분리 시켜 편리성을 JDBC에 비해 향상 시켰습니다. 그리고 프레임워크에서 데이터베이스연동에 필요한 자바 코드를 제공함으로써 개발자가 작성해야했던 수많은 코드를 제거할 수 있었습니다. 하지만 이런 장점들에도 불구하고 여전히 개발자가 직접 <code>SQL</code>을 관리하기 때문에 데이터 구조가 변경되는 상황에서는 효율적으로 대처하기가 어렵습니다.</p>

<h3>4. Hibernate 프레임워크</h3>

<p><code>마이바티스</code>의 문제를 해결하기 위해 만들어진 것은 <code>하이버네이트</code>같은 <code>ORM(Object-Relational-Mapping)프레임워크</code> 입니다. <code>ORM</code>은 <code>객체(Value Object)</code>와 <code>테이블의 ROW</code>를 자동으로 매핑해주는 프레임워크라고 할 수 있습니다. 자바의 객체를 테이블과 매핑하기 위해서는 <code>데이터베이스에 전달할 SQL</code>과 <code>SQL을 전송하기 위한 자바코드</code>가 필요합니다. <code>ORM</code>은 이 두 가지 모두를 프레임워크에서 제공합니다.</p>

<h4>다이얼렉트(Dialect) 클래스</h4>

<p><code>ORM</code>은 데이터베이스 연동에 필요한 <code>SQL</code>을 프레임워크에서 생성합니다. 하지만, 각각의 데이터베이스마다 사용하는 <code>SQL</code>이 다른데, 이는 <code>Hibernate</code>가 어떤 데이터베이스에 최적화된 <code>SQL</code>을 생성할지 알고있어야 합니다. 이때 <code>Hibernate</code>가 참조하는 정보는 <code>다이얼렉트(Dialect)</code> 클래스입니다.</p>

<h4>하이버네이트와 데이터베이스의 연동 과정의 예</h4>

<p> <code>SessionFactory</code>에서 <code>Session</code>객체를 획득 => <code>Session</code>객체의 <code>persist</code>메소드를 통해 <code>insertEmplyee</code> 메서드를 구현</p>

<h4>HQL(Hibernate Query Language)</h4>

<p> <code>HQL</code>은 하이버네이트가 제공하는 특수한 쿼리 언어로서 기본 구조는 <code>SQL</code>과 유사합니다. 대소문자를 명확하게 구분하여 작성해야 합니다.</p>

<p><code>하이버네이트 프레임워크</code>를 이용하면 테이블 구조가 변경되는 상황에서도 기존 소스에 미치는 영향이 거의 없습니다. <code>하이버네이트</code>는 데이터 구조가 변경되면 내부적으로 <code>ALTER TABLE</code>명령어를 처리하여 자동으로 구조를 변경해줍니다. 상세 로직은 <code>하이버네이트</code>가 제공해주기 때문에 클래스나 메소드의 수정도 할 필요가 없습니다. 개발자는 <code>VO</code>클래스에 변수를 추가 선언만 하면 되는 것 처럼 데이터 관리에 얽매이지 않아도 됩니다.</p>

<hr />

<h3>1. JPA</h3>

<p> <code>ORM</code>의 시초는 사실 <code>EJB(Enterprise Java Beans)</code>의 <code>엔티티 빈(Entity Bean)</code>이라고 할 수 있습니다. 하지만, <code>EJB</code>의 <code>엔티티 빈</code>은 느린 성능과 구현의 복잡함, 그리고 데이터베이스 테이블과 완벽하게 매핑하지 못한다는 문제로 개발자들로부터 외변받았습니다. 이런 <code>엔티티 빈</code>의 대안으로 <code>하이버네이트</code>나 오라클의 <code>탑링크(TopLink)</code>같은 <code>ORM 프레임워크</code>들이 등장하게 되었습니다. 자바에서도 이런 <code>ORM 프레임워크</code>들과 경쟁하기 위해 <code>엔티티 빈</code>을 과감하게 포기하고 <code>Java Persistence API(JPA)</code>라는 새로운 스펙을 발표하게 되었습니다.</p>
<p> 중요한 사실은 <code>하이버네이트</code>를 비롯한 다른 <code>ORM</code> 기술은 "자바 표준"이 아니며, <code>EJB</code>의 <code>엔티티 빈</code>과 <code>JPA</code>는 "자바 표준"이라는 것입니다.</p>
<p><code>JPA</code>는 실제로 <code>JPA</code>를 구현한 구현체가 구체적인 데이터베이스 연동을 처리하는 개념입니다. 여기에서 <code>JPA</code> 구현체는 <code>JDBC</code>에서 드라이버와 동일한 개념이기 때문에 유지보수 과정에서 <code>JPA</code>구현체를 얼마든지 다른것으로 변경할 수 있습니다.</p>

<h4>JPA의 영속성 과정</h4>

![otlcg_jd_001](https://github.com/OliveLover/JPA-QuickStart/assets/118647313/d691325b-f94f-4a43-b3ba-eca73a79783e)

**이미지 출처** : [Oracle](https://docs.oracle.com/middleware/1212/toplink/OTLCG/blocks.htm#OTLCG135)


<p><code>Persistence</code>가 <code>JPA 메인 설정 파일(META-INF/persistence.xml)</code>파일을 로딩 => <code>XML</code>에 설정된 메타데이터를 이용하여 <code>EntityManagerFactory</code>객체를 생성 => <code>EntityManagerFactory</code>로 부터 <code>EntityManager</code>객체를 획득 => <code>EntityManager</code>를 통해 영속성 관리</p>

<h3>2. @Entity</h3>

<p><code>@Entity</code>는 생략할 수 없는 필수 어노테이션입니다. <code>JPA</code>는 <code>@Entity</code>가 설정된 클래스로 부터 생성된 객체만 엔티티로 인지하고 사용할 수 있습니다. 테이블에 저장된 각 <code>ROW</code>는 <code>기본 키(Primary Key)</code>를 통해 유일한 데이터로 식별됩니다. 따라서 테이블의 기본 키 칼럼과 엔티티의 식별자 필드가 매핑되도록 식별자 필드에 <code>@Id</code>를 설정해야 합니다.</p>

```
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
  @Id
  private Long id;

(...)
```

<p><code>@Entity</code>가 설정된 엔티티 클래스는 다른 엔티티 클래스와 구분하기 위해 유일한 엔티티의 이름을 가지고 있어야 하며, 위 코드 처럼 <code>@Entity</code>에 <code>name</code>속성을 지정하지 않으면 <code>JPA</code>는 클래스 이름에 해당하는 <code>Employee</code>를 엔티티 이름으로 등록합니다.</p>

<h3>3. @Table</h3>

|속성|설명|
|:---|:---|
|name|매핑할 테이블 이름을 지정|
|catalog|데이터베이스 카탈로그(catalog)를 지정(MySQL)|
|schema|데이터베이스 스키마(schema)를 지정(Oracle)|
|uniqueConstraints|결합 unique 제약 조건을 지정하며, 여러 개의 칼럼이 결합되어 유일성을 보장해야 하는 경우 사용|

```
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name = "S_EMP",
   uniqueConstraints = {@UniqueConstraint(columnNames={ "NAME", "MAILID" }) })
public class Employee {

(...)

```

```
Hiberante:
    alter table S_EMP
        add constraint UKguwrlsjy5yfqr5a1iomntwv6e unique (name, mailId)
```

<h3>4. @Column</h3>

|속성|설명|기본 값|
|:---|:---|:---:|
|name|매핑될 칼럼 이름을 지정(생략 시 변수 이름과 동일한 칼럼 매핑)||
|unique|unique 제약조건 설정|false|
|nullable|null 허용 여부 설정|false|
|insertable|INSERT SQL을 생성할 때 이 칼럼을 포함할지 설정|true|
|updatable|UPDATE SQL을 생성할 때 이 칼럼을 포함할지 설정|true|
|columnDefinition|이 칼럼에 대한 DDL문을 직접 기술||
|length|문자열 타입의 칼럼 길이를 지정|255|
|precision|숫자 타입의 전체 자릿수를 지정|0|
|scale|숫자 타입의 소수점 자릿수를 지정|0|

```
import javax.persistence.Column;

@Data
@Entity
@Table(name = "S_EMP",
  uniqueConstraints = {@UniqueConstraint(columnNames = { "NAME", "MAILID" }) })
public class Employee {

  @Id
  @Column(length = 7, nullable = false)
  private Long id;

  @Column(length = 25, nullable = false)
  private String name;

  @Column(length = 8, unique = true)
  privat String mailId;

  @Column(name = "START_DATE", insertable = false)
  private Date startDate;

  @Column(length = 25)
  private String title;

  @Column(name = "DEPT_NAME", length = 30)
  private String deptName;

  @Column(precision = 11, scale = 2)
  private Double salary;

  @Column(name = "COMMISSION_PCT", precision = 4, scale = 2,
        columnDefinition = "double CHECK (commission_pct IN (10, 12.5, 15, 17.5, 20))")
  private Double commissionPct;
}
```
```
Hibernate:
  create table S_EMP (
    id bigint not null,
    COMMISSION_PCT double CHECK (commission_pct IN (10, 12.5, 15, 17.5, 20)),
    DEPT_NAME varchar(30),
    mailId varchar(8),
    name varchar(25) not null,
    salary double,
    START_DATE timestamp,
    title varchar(25),
    primary key (id)

)

Hibernate:
  alter table S_EMP
    add constraint UKguwrlsjy5yfqr5a1iomntwv6e unique (name, mailId)

Hibernate:
  alter table S_EMP
    add constraint UK_24iet9wlcsxklp80t4yl9fo1t unique (mailId)

Hibernate:
  insert
  into
    S_EMP
    (COMMISSION_PCT, DEPT_NAME, mailId, name, salary, title, id)
  values
    (?, ?, ?, ?, ?, ?, ?)
```

<p>insertable = false 가 설정된 startDate 변수는 INSERT 구문이 생성될때, START_DATE 칼럼이 INSERT 구문에서 제외된 것을 확인 할 수 있습니다.</p>
