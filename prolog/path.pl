edge(nyc,chicago).
edge(nyc,dallas).
edge(chicago, la).
edge(dallas, la).
edge(la, sfo).
edge(sfo,la). 
edge(la, dallas).
edge(dallas, nyc).

path2(Start,End,Path) :- path(Start,End,[Start],Trace), reverse(Trace,Path).
path(Start,End,P,[End|P]) :- edge(Start,End).
path(Start,End,PathList,Path) :- edge(Start,Z), Z\==End, \+member(Z,PathList), path(Z,End,[Z|PathList],Path).  