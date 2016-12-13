x = 0:1:25;
y = [2,1,14,34,56,94,189,266,330,416,507,580,610,513,593,557,560,522,565,517,500,585,500,495,525,510];

plot(x,y);
title('Exercise 1 plot P.aurelia')
xlabel('days')
ylabel('Poplation density')
hold on

y = lsode("lotkavoltraPaurelia",2,x);

plot(x,y,'r');
