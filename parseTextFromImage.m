
puzzle1=imread('3.6.ara.lg.20160417_172851.jpg');
sizzle1=size(puzzle1);
segNum=1;
puzzleIn=puzzle1(1:segNum:sizzle1(1),1:segNum:sizzle1(2),:);


colorCar=2;
blueCar = double((puzzle1(:,:,colorCar)));
[dum,ib]=sort(blueCar(:));

%%
figure(1)
subplot(1,2,1),imshow(puzzle1(1:segNum:sizzle1(1),1:segNum:sizzle1(2),:))
subplot(1,2,2),plot(dum,'b.')

%%
[bythenumber,numPieces]=bwlabeln(puzzleIn(:,:,3)<155);
a=regionprops(bythenumber,'FilledArea','BoundingBox'); % 'BoundingBox'

%%
figure(2)
imshow(label2rgb(bythenumber))

%%
sizePiece=zeros(numPieces,1);
boxPiece=zeros(numPieces,4);
for i=1:numPieces
    sizePiece(i)=a(i).FilledArea;
    boxPiece(i,:)=uint16(a(i).BoundingBox);
end
%%
sortSize=sort(sizePiece);
figure(3)
%plot((sizePiece(4:length(sizePiece))),'.')
plot(sortSize(1:length(sortSize)-1),'.')

%%
maskPiece=(sizePiece>50)&(sizePiece<5000);
ahypoth=sum(maskPiece);
selectPieces=find(maskPiece);
[rankPieces,indexPieces]=sort(sizePiece(selectPieces));
hypPuzzlePieces=bythenumber==-255;
rankPieces=selectPieces(indexPieces);
figure(6)
plot(rankPieces,'.')

