<h1>JPA-Quick start</h1>

<h3>JDBC의 문제</h3>

<p> 커넥션을 연결하고 종료하는 중복되는 코드를 별도의 메소드(<code>getConnection</code>, <code>close</code>)로 분리시켰음에도 불구하고 여전히 모든 데이터베이스 연동 메서드(<code>insertEmployee</code>, <code>getEmployeeList</code>)에 "반복되는 코드"가 등장합니다.</p>


<h3>MyBatis의 문제</h3>
<p> MyBatis에서 직접 SQL-Mapper를 작성할 때 발생하는 오류를 정확하게 잡기 어려워서 오타와 같은 부분에 대하여 많은 어려움이 있었습니다. MyBatis와 같은 Data-Mapper는 자바코드와 SQL을 분리 시켜 편리성을 JDBC에 비해 향상 시켰습니다. 그리고 프레임워크에서 데이터베이스연동에 필요한 자바 코드를 제공함으로써 개발자가 작성해야했떤 수많은 코드를 제거할 수 있었습니다. 하지만 이런 장점들에도 불구하고 여전히 개발자가 직접 SQL을 관리하기 때문에 데이터 구조가 변경되는 상황에서는 효율적으로 대처하기가 어렵습니다.</p>
