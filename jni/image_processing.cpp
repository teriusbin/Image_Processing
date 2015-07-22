#include "image_processing.h"

#include <jni.h>
#include <android/log.h>
#include <stdlib.h>
#include <stdio.h>


unsigned char **image2DMemAlloc( int width, int height)
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

unsigned char *memoryCopy2Dto1D(unsigned char **srcdata, int width, int height){

	unsigned char *tempData = new unsigned char[width*height];

	for (int i = 0; i<(height); i++){ // 1차원 입력 영상의 값을 2차원 배열에 할당한다.

			  for (int j = 0; j<(width); j++){

				  tempData[i * width + j] = (unsigned char)srcdata[i][j];
			}
	}

	return tempData;

}

unsigned char **memoryCopy1Dto2D(unsigned char *srcdata, int width, int height){

	unsigned char **tempData = image2DMemAlloc(width, height);

	for (int i = 0; i<(height); i++){ // 1차원 입력 영상의 값을 2차원 배열에 할당한다.

			  for (int j = 0; j<(width); j++){

				  tempData[i][j] = (unsigned char)srcdata[i * (width) + j];

				 }
		 }
	return tempData;

}

unsigned char **memoryCopy2DTo2D(unsigned char **inputImage, int width, int height)
{

	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j]);

		 }

	 }

	 return OutputImage;
}

unsigned char **FSFunc(unsigned char **inputImage, int width, int height, struct FSFuncParameter paramSet)
{


	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.FS_param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.FS_param1);

		 }

	 }

	 return OutputImage;
}
unsigned char **HDRGlareFunc(unsigned char **inputImage, int width, int height, struct HDRGlareFuncParameter paramSet)
{


	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.HDRGlare_param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.HDRGlare_param1);

		 }

	 }

	 return OutputImage;
}
unsigned char **NRFunc(unsigned char **inputImage, int width, int height, struct NRFuncParameter paramSet)
{


	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.NR_param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.NR_param1);

		 }

	 }

	 return OutputImage;
}
unsigned char **FADEFunc(unsigned char **inputImage, int width, int height, struct FADEFuncParameter paramSet)
{


	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.FADE_param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.FADE_param1);

		 }

	 }

	 return OutputImage;
}
unsigned char **CSFunc(unsigned char **inputImage, int width, int height, struct CSFuncParameter paramSet)
{


	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.CS_param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.CS_param1);

		 }

	 }

	 return OutputImage;
}

unsigned char **CCFunc(unsigned char **inputImage, int width, int height, struct CCFuncParameter paramSet)
{


	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.CC_param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.CC_param1);

		 }

	 }

	 return OutputImage;
}

unsigned char **ASCRFunc(unsigned char **inputImage, int width, int height, struct ASCRFuncParameter paramSet)
{


	unsigned char **OutputImage = image2DMemAlloc(width, height);

	 for(int i=0 ; i<(height); i++){

		 for(int j=0 ; j<(width) ; j++){

			 if(inputImage[i][j] + paramSet.ASCR_param1 >= 255)

				 OutputImage[i][j]= 255;

			 else

				 OutputImage[i][j] = (unsigned char)(inputImage[i][j] +paramSet.ASCR_param1);

		 }

	 }

	 return OutputImage;
}

