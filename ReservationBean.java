package javaproject;

/*빈즈(Beans:콩) 만드는 순서
 * 1.테이블 컬럼별로 private 변수 선언
 * 2.getter & setter 만들기
 * 3.빈즈는 테이블의 레코드를 담는 데이터 덩어리*/

public class ReservationBean/* 테이블명+Bean */ {
	// 컬럼별 private 변수 선언
	private String id;
	private int r_room;
	private int startdate;
	private int enddate;
	private int r_capacity;
	private String r_status;
	private int p_cost;
	private int res_no;

	// 디폴트 생성자
	public ReservationBean() {

	}

	// 매개변수 생성자
	public ReservationBean(String id, int r_room, int startdate,
			int enddate, int r_capacity, String r_status,
			int p_cost, int res_no) {
		this.id = id;
		this.r_room = r_room;
		this.startdate = startdate;
		this.enddate = enddate;
		this.r_capacity = r_capacity;
		this.r_status = r_status;
		this.p_cost = p_cost;
		this.res_no = res_no;
	}

	// getter & setter 생성
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getR_room() {
		return r_room;
	}

	public void setR_room(int r_room) {
		this.r_room = r_room;
	}

	public int getStartdate() {
		return startdate;
	}

	public void setStartdate(int startdate) {
		this.startdate = startdate;
	}

	public int getEnddate() {
		return enddate;
	}

	public void setEnddate(int enddate) {
		this.enddate = enddate;
	}

	public int getR_capacity() {
		return r_capacity;
	}

	public void setR_capacity(int r_capacity) {
		this.r_capacity = r_capacity;
	}

	public String getR_status() {
		return r_status;
	}

	public void setR_status(String r_status) {
		this.r_status = r_status;
	}

	public int getP_cost() {
		return p_cost;
	}

	public void setP_cost(int p_cost) {
		this.p_cost = p_cost;
	}

	public int getRes_no() {
		return res_no;
	}

	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}

}
