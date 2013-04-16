package com.squareup.picasso.transformations;

import android.graphics.Bitmap;
import com.squareup.picasso.PicassoTestRunner;
import com.squareup.picasso.Transformation;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(PicassoTestRunner.class)
public class ResizeTransformationTest {
  @Test public void sameSizeReturnsSource() {
    Bitmap source = Bitmap.createBitmap(10, 10, null);
    ResizeTransformation transformation = new ResizeTransformation(10, 10);
    Bitmap actual = transformation.transform(source);
    assertThat(actual).isSameAs(source);
    assertThat(actual).isNotRecycled();
  }

  @Test public void resizeDown() {
    Bitmap source = Bitmap.createBitmap(10, 10, null);
    ResizeTransformation transformation = new ResizeTransformation(5, 5);
    Bitmap actual = transformation.transform(source);
    assertThat(actual).isNotSameAs(source).hasWidth(5).hasHeight(5).isNotRecycled();
    assertThat(source).isRecycled();
  }

  @Test public void resizeUp() {
    Bitmap source = Bitmap.createBitmap(10, 10, null);
    ResizeTransformation transformation = new ResizeTransformation(55, 55);
    Bitmap actual = transformation.transform(source);
    assertThat(actual).isNotSameAs(source).hasWidth(55).hasHeight(55).isNotRecycled();
    assertThat(source).isRecycled();
  }

  @Test public void keyDependsOnWidth() {
    Transformation transformation1 = new ResizeTransformation(5, 0);
    Transformation transformation2 = new ResizeTransformation(10, 0);
    assertThat(transformation1.key()).isNotEqualTo(transformation2.key());
  }

  @Test public void keyDependsOnHeight() {
    Transformation transformation1 = new ResizeTransformation(0, 5);
    Transformation transformation2 = new ResizeTransformation(0, 10);
    assertThat(transformation1.key()).isNotEqualTo(transformation2.key());
  }

  @Test public void keyIsNotReferenceBased() {
    Transformation transformation1 = new ResizeTransformation(5, 0);
    Transformation transformation2 = new ResizeTransformation(5, 0);
    assertThat(transformation1.key()).isEqualTo(transformation2.key());
  }

}
