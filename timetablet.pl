
time(1..15).
student(mario;luigi;peach).%;pls;luigi;nothanks
takes(mario,science;luigi,systemsArch;peach,science).
room(uniHall,10;wumpusDen,10;fabiosOffice,10).%;wumpusDen;fabiosOffice).
unit(systemsArch,fabio;  science,nicolai;  ai,deVos;  dd,myNamesJeff).
nchuncks(1,systemsArch;3,ai;2,science;1,dd).
lecturerPref(myNamesJeff,1;nicolai,15;fabio,7;deVos,4).
otherComm(deVos;13).


N {lec(T,U,L,R,C,PR) : time(T),room(R,C),unit(U,L),lecturerPref(L,PR)} N :- nchuncks(N,U).
0 {stu(S,T,U,R,C):   lec(T,U,_,R,C,_)     } N :- nchuncks(N,_),student(S),time(T).

%:- stu(_,T,U1), lec(T,U2,_,_,_,_), U1!=U2.
:- lec(T,U1,L,_,_,_),lec(T,U2,L,_,_,_), U1 != U2.
:- lec(T,_,L1,R,_,_),lec(T,_,L2,R,_,_), L1 != L2.
:- lec(T,_,L,R1,_,_),lec(T,_,L,R2,_,_), R1 != R2.

:- lec(T,_,L,_,_,_),otherComm(L,T).

:- not stu(S,_,U,_,_),lec(_,U,_,_,_,_),takes(S,U).

:- stu(S,_,U1,_,_),lec(_,U2,_,_,_,_),takes(S,U3),U1!=U3.
:- stu(S,_,U,_,_),lec(T,U,_,_,_,_),lec(T,U1,_,_,_,_),takes(S,U),U!=U1.

size(T,R,N) :- N = #count {S : stu(S,T,_,R,_), room(R,_),time(T) },time(T),room(R,_).
:- lec(T,_,_,R,C,_), size(T,R,N), C<N.


:~ lec(T,_,L,_,_,PR),T!=PR. [10,T,L,PR]%JESUS!!!
:~ lec(T,_,_,_,_,_),T==2. [10,T]
:~ lec(T,_,_,_,_,_),T==6. [10,T]
:~ lec(T,_,_,_,_,_),T==10. [10,T]
:~ lec(T,_,_,_,_,_),T==14. [10,T]
:~ lec(T,_,_,_,_,_),T==18. [10,T]
#show lec/6.
#show stu/5.