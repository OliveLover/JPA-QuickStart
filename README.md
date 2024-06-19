<h1>JPA-Quick start</h1>

<h3>JDBC의 문제</h3>
<ul>
  <li>커넥션을 연결하고 종료하는 중복되는 코드를 별도의 메소드(<code>getConnection</code>, <code>close</code>)로 분리시켰음에도 불구하고 여전히 모든 데이터베이스 연동 메서드(<code>insertEmployee</code>, <code>getEmployeeList</code>)에 "반복되는 코드"가 등장합니다.</li>
</ul>
