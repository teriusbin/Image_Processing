#include "image_processing.h"

#include <jni.h>
#include <android/log.h>
#include <stdlib.h>

unsigned char **Image2DMem( int width, int height)
{
	 unsigned char** temp;

	 temp = new unsigned char *[height]; //memory ���� ��ŭ �Ҵ�

	 for(int i=0 ; i<height ; i++)
	 {
		  temp[i] = new unsigned char[width]; // �޸� �ʺ� ��ŭ �Ҵ�
	 }

	 for(int i=0 ; i<height ; i++){ //�ʱ�ȭ

		 for(int j=0 ; j<width ; j++){

			 temp[i][j] = 0.0;

		 }
	 }

	 return temp;
}

unsigned char *MemoryCopy2Dto1D(unsigned char **srcdata, int width, int height){

	unsigned char *tempData = new unsigned char[width*height];

	for (int i = 0; i<(height); i++){ // 1���� �Է� ������ ���� 2���� �迭�� �Ҵ��Ѵ�.

			  for (int j = 0; j<(width); j++){

				  tempData[i * width + j] = (unsigned char)srcdata[i][j];
			}
	}

	return tempData;

}

unsigned char **MemoryCopy1Dto2D(unsigned char *srcdata, int width, int height){

	unsigned char **tempData = Image2DMem(width, height);

	for (int i = 0; i<(height); i++){ // 1���� �Է� ������ ���� 2���� �迭�� �Ҵ��Ѵ�.

			  for (int j = 0; j<(width); j++){

				  tempData[i][j] = (unsigned char)srcdata[i * (width) + j];

				 }
		 }
	return tempData;

}

unsigned char **ImageProcessing(unsigned char **inputImage, int width, int height)
{

	unsigned char **OutputImage = Image2DMem(width, height);

	 for(int i=0 ; i<(height); i++){ //�ʱ�ȭ

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + 50 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +50);

		 }

	 }

	 return OutputImage;
}

