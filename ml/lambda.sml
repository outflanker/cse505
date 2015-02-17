(*CSE 505 lambda.sml. Author: ssinha4 (Siddharth Krishna Sinha)*)

(*Lambda term datatype*)
datatype term = V of string | L of string * term | A of term * term;

(*Function to represent Lambda expression*)
fun show(V(x))=x
| show(L(x,t))="L"^x^"."^show(t)
| show(A(t1,t2))="("^show(t1)^" "^show(t2)^")";

(*Function to check for alpha equivalence of lambda expression*)
fun alpha(t1,t2)=alpha2(t1,t2,[])

(*Helper function for alpha*)
and alpha2(V(x),V(y),env)=check_variable(x,y,env)
|alpha2(L(x1,t1),L(x2,t2),env)= alpha2(t1,t2,env@[(x1,x2)])
|alpha2(A(t1,t2),A(t3,t4),env)= alpha2(t1,t3,env) andalso alpha2(t2,t4,env)
|alpha2(_,_,env)=false

(*Helper function to check for variable binding*)
and check_variable(x,y,[])= if x=y
							then true
							else false
|check_variable(x,y,(v1, v2) :: t)=if (x=v1 andalso y=v2) orelse (x<>v1 andalso y<>v2)
								   then true
								   else false;