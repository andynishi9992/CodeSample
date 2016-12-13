
% t = time(days), x = P.aurelia population, y = P.caudatm population
%

t = 0:1:25;


x = [2,1,10,21,58,92,202,163,221,293,236,303,302,340,387,335,363,323,358,308,350,330,350,350,330,350];
y = [2,1,10,11,29,50,88,102,124,93,80,66,83,55,67,52,55,40,48,47,50,40,20,20,25,20];
plot(t,x,'r');
hold on;
plot(t,y,'c');
XX = lsode("lotkavoltracompetition",[2;2],t);
plot(t,XX);
hold on
title('Exercise 2 plot P.aurelia and P.caudatum')
xlabel('days')
ylabel('Poplation density')
hold on


