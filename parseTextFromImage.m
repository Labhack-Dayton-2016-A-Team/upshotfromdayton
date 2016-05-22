
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
[orderPieces,indexPieces]=sort(sizePiece(selectPieces));
rankPieces=selectPieces(indexPieces);
figure(6)
plot(orderPieces,'.')

%%
numInfo=(zeros(ahypoth,1));
sortedPuzzle=uint8(zeros(size(puzzle1)));
for i=1:ahypoth
    j=rankPieces(i);
    %boxPiece=uint16(a(j).BoundingBox);
    boxRangeX=boxPiece(j,2)-1 + (0:boxPiece(j,4));
    boxRangeY=boxPiece(j,1)-1 + (0:boxPiece(j,3));
    %imshow(puzzle1(boxRangeX,boxRangeY,:))
    %pause(1)
    numInfo(i)=sum(sum(puzzle1(boxRangeX,boxRangeY,1)<30));
    if numInfo(i)>10
        sortedPuzzle(boxRangeX,boxRangeY,:)=puzzle1(boxRangeX,boxRangeY,:);
    end
end
%%


[dum,indexInfo]=sort(numInfo,'descend');
lowInfo=find(dum<10);
limitInfo=lowInfo(01);
getPieces=boxPiece(rankPieces(indexInfo(1:limitInfo)),:);
listPiecesByInfo=uint8(zeros(max(getPieces(:,4)),limitInfo+sum(getPieces(:,3)),3));

offsetY=0;
for i=1:limitInfo
    boxRangeX=getPieces(i,2)-1 + (0:getPieces(i,4));
    boxRangeY=getPieces(i,1)-1 + (0:getPieces(i,3));
    listRangeX=1+(0:getPieces(i,4));
    listRangeY=1+(0:getPieces(i,3))+offsetY;
    totalPixels=(1+getPieces(i,4))*(1+getPieces(i,3));
    %imshow(puzzle1(boxRangeX,boxRangeY,:))
    %title(int2str(i))
    %pause(0.1)
    listPiecesByInfo(listRangeX,listRangeY,:)=puzzle1(boxRangeX,boxRangeY,:);
    offsetY=offsetY+getPieces(i,3)+1;
end

%%
figure(7)
imshow(listPiecesByInfo)
