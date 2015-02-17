/**
 * Inheritance and Delegation
 * @author Siddharth
 *
 */
public class Delegation {
	public static void main(String args[]) {
		C111 c111 = new C111();
		System.out.println(c111.m111());

		C112 c112 = new C112();
		System.out.println(c112.m112());
		
		D111 d111 = new D111();
		System.out.println(d111.m111());

		D112 d112 = new D112();
		System.out.println(d112.m112());
	}
}

class C1 {
	protected int a1 = 1;

	public int m1() {
		return a1 + p1(100) + q1(100);
	}

	public int p1(int m) {
		return m;
	}
	
	public int q1(int m) {
		return m;
	}
}

 class C11 extends C1 {
	protected int a11 = 11;

	public int m11() {
		return m1() + q1(200);
	}

	public int p1(int m) {
		return m * a1;
	}

	public int q1(int m) {
		return m + a11;
	}
}

class C111 extends C11 {
	protected int a111 = 111;

	public int m111() {
		return m1() + m11() + a111;
	}
	
	public int p1(int m) {
		return m * a1 * a11;
	}
}

class C112 extends C11 {
	protected int a112 = 112;

	public int m112() {
		return m1() + m11() + a112;
	}

	public int p1(int m) {
		return m * a1 * a11 * a112;
		
	}
}
// -------SIMULATING CLASS INHERITANCE BY DELEGATION ---------

interface I1 {
	int getA1();
	int m1();
	int p1(int m);
	int q1(int m);
}

interface I11 extends I1 {
	int getA11();
	int m11();
}

interface I111 extends I11 {
	int getA111();
	int m111();
}

interface I112 extends I11 {
	int getA112();
	int m112();
}

class D1 implements I1 {
	protected int a1 = 1;
	private I1 i;
	public D1(){
		this.i=this;
	}
	public D1(I1 i){
		this.i=i;
	}
	public int getA1() {
		return this.a1;
	}
	public int m1() {
		return getA1()+i.p1(100)+i.q1(100);
	}	 
	public int p1(int m) {
		return m;
	}	
	public int q1(int m) {
		return m;
	}
}

class D11 implements I11 {
	protected int a11 = 11;
	private I11 i;
	private I1 d1;
	public D11(){
		this.i=this;
		d1=new D1(i);
	}
	public D11(I11 i){
		this.i=i;
		d1=new D1(i);
	}
	@Override
	public int getA1() {
		return d1.getA1();
	}
	@Override
	public int getA11() {
		return this.a11;
	}
	@Override
	public int m11() {
		return i.m1()+i.q1(200);
	}	
	@Override
	public int m1() {
		return d1.m1();
	}
	@Override
	public int p1(int m) {
		return m*this.getA1();
	}
	@Override
	public int q1(int m) {
		return m+this.getA11();
	}	
}
class D111 implements I111 {
	protected int a111=111;
	private I111 i;
	private I11 d1;
	
	public D111(){
		this.i=this;
		d1=new D11(i);
	}	
	@Override
	public int getA111() {
		return this.a111;
	} 
	@Override
	public int getA11() {
		return d1.getA11();
	}
	@Override
	public int getA1() {
		return d1.getA1();
	}
	@Override
	public int m11() {
		return d1.m11();
	}
	@Override
	public int m1() {
		return d1.m1();
	}
	@Override
	public int q1(int m) {
		return d1.q1(m);
	}
	@Override
	public int m111() {
		return i.m1() + i.m11() + getA111();
	}
	@Override
	public int p1(int m) {
		return m * getA1() * getA11();
	}
}
class D112 implements I112 {
	protected int a112=112;
	private I112 i;
	private I11 d11;
	
	public D112(){
		this.i=this;
		d11=new D11(i);
	}
	@Override
	public int getA112() {
		return this.a112;
	} 
	@Override
	public int getA11() {
		return d11.getA11();
	}
	@Override
	public int getA1() {
		return d11.getA1();
	}
	@Override
	public int m11() {
		return d11.m11();
	}
	@Override
	public int m1() {
		return d11.m1();
	}
	@Override
	public int q1(int m) {
		return d11.q1(m);
	}
	@Override
	public int m112() {
		return i.m1() + i.m11() + getA112();
	}
	@Override
	public int p1(int m) {
		return m * getA1() * getA11()*getA112();
	}
}