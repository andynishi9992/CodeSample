[X,Y] = meshgrid(-50:20:800,-120:10:220);

r=.8;
a=2.4;
k=550;
r1=.7;
a1=.38;
k1=200;
u =r.*X.*(1-((X+a.*Y)/k));
v =r1.*Y.*(1-((Y+a1.*X)/k1));
figure
contour(X,Y,u,[0,0],'linewidth',4);
hold on
contour(X,Y,v,[0,0],'r','linewidth',4);
hold on
quiver(X,Y,u,v);
hold off

