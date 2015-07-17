#include "image_processing.h"

#include <jni.h>
#include <android/log.h>
#include <stdlib.h>
#include <stdio.h>

unsigned char **Image2DMem( int width, int height)
{
	 unsigned char** temp;

	 temp = new unsigned char *[height]; //memory 높이 만큼 할당

	 for(int i=0 ; i<height ; i++)
	 {
		  temp[i] = new unsigned char[width]; // 메모리 너비 만큼 할당
	 }

	 for(int i=0 ; i<height ; i++){ //초기화

		 for(int j=0 ; j<width ; j++){

			 temp[i][j] = 0.0;

		 }
	 }

	 return temp;
}

unsigned char *MemoryCopy2Dto1D(unsigned char **srcdata, int width, int height){

	unsigned char *tempData = new unsigned char[width*height];

	for (int i = 0; i<(height); i++){ // 1차원 입력 영상의 값을 2차원 배열에 할당한다.

			  for (int j = 0; j<(width); j++){

				  tempData[i * width + j] = (unsigned char)srcdata[i][j];
			}
	}

	return tempData;

}

unsigned char **MemoryCopy1Dto2D(unsigned char *srcdata, int width, int height){

	unsigned char **tempData = Image2DMem(width, height);

	for (int i = 0; i<(height); i++){ // 1차원 입력 영상의 값을 2차원 배열에 할당한다.

			  for (int j = 0; j<(width); j++){

				  tempData[i][j] = (unsigned char)srcdata[i * (width) + j];

				 }
		 }
	return tempData;

}

unsigned char **memcpy2DTo2D(unsigned char **inputImage, int width, int height)
{

	unsigned char **OutputImage = Image2DMem(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j]);

		 }

	 }

	 return OutputImage;
}

unsigned char **ImageProcessing1(unsigned char **inputImage, int width, int height, struct ImageProcessing1 paramSet)
{


	unsigned char **OutputImage = Image2DMem(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.param1);

		 }

	 }

	 return OutputImage;
}
unsigned char **ImageProcessing2(unsigned char **inputImage, int width, int height, struct ImageProcessing2 paramSet)
{


	unsigned char **OutputImage = Image2DMem(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.param1);

		 }

	 }

	 return OutputImage;
}
unsigned char **ImageProcessing3(unsigned char **inputImage, int width, int height, struct ImageProcessing3 paramSet)
{


	unsigned char **OutputImage = Image2DMem(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.param1);

		 }

	 }

	 return OutputImage;
}
