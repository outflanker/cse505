occurs_free_in(X,v(X)).
occurs_free_in(X,l(Y,T)):- X\==Y, occurs_free_in(X,T).
occurs_free_in(X,a(T1,T2)):- occurs_free_in(X,T1); occurs_free_in(X,T2).

eta(l(X,a(T,Y))):- occurs_free_in(X,Y),\+ occurs_free_in(X,T).

beta(a(l(X,T1),T2)).

betaRecurse(a(l(X,T),_)).
betaRecurse(a(T1,T2)):-betaRecurse(T1);betaRecurse(T2).	
betaRecurse(l(X,T)):-betaRecurse(T).

etaRecurse(l(X,a(T,v(X)))):- \+occurs_free_in(X, T);etaRecurse(T).
etaRecurse(a(T1,T2)):- etaRecurse(T1);etaRecurse(T2).

normal(T):- \+ etaRecurse(T), \+ betaRecurse(T).