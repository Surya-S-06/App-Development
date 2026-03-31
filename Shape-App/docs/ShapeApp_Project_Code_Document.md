# Shape App Project Code

## Java Code

### MainActivity.java
```java
package com.example.shapeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupShape;
    private RadioGroup radioGroupColor;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupShape = findViewById(R.id.radioGroupShape);
        radioGroupColor = findViewById(R.id.radioGroupColor);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedShapeId = radioGroupShape.getCheckedRadioButtonId();
                int selectedColorId = radioGroupColor.getCheckedRadioButtonId();

                if (selectedShapeId == -1 || selectedColorId == -1) {
                    Toast.makeText(MainActivity.this, R.string.error_selection, Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton shapeButton = findViewById(selectedShapeId);
                RadioButton colorButton = findViewById(selectedColorId);

                String shape = shapeButton.getText().toString();
                String color = colorButton.getText().toString();

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                intent.putExtra("SHAPE", shape);
                intent.putExtra("COLOR", color);
                startActivity(intent);
            }
        });
    }
}
```

### DisplayActivity.java
```java
package com.example.shapeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String shape = getIntent().getStringExtra("SHAPE");
        String colorName = getIntent().getStringExtra("COLOR");

        int colorCode = Color.BLACK; // Default
        if (colorName != null) {
            switch (colorName) {
                case "Red":
                    colorCode = Color.RED;
                    break;
                case "Blue":
                    colorCode = Color.BLUE;
                    break;
                case "Green":
                    colorCode = Color.GREEN;
                    break;
                case "Yellow":
                    colorCode = Color.YELLOW;
                    break;
            }
        }

        FrameLayout container = findViewById(R.id.shapeContainer);
        ShapeView shapeView = new ShapeView(this, shape, colorCode);
        container.addView(shapeView);
    }
}
```

### ShapeView.java
```java
package com.example.shapeapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class ShapeView extends View {

    private String shapeType;
    private int color;
    private Paint paint;

    public ShapeView(Context context, String shape, int color) {
        super(context);
        this.shapeType = shape;
        this.color = color;
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height) / 2;
        int cx = width / 2;
        int cy = height / 2;

        if (shapeType.equalsIgnoreCase("Circle")) {
            canvas.drawCircle(cx, cy, size, paint);
        } else if (shapeType.equalsIgnoreCase("Square")) {
            canvas.drawRect(cx - size, cy - size, cx + size, cy + size, paint);
        } else if (shapeType.equalsIgnoreCase("Triangle")) {
            Path path = new Path();
            path.moveTo(cx, cy - size); // Top
            path.lineTo(cx - size, cy + size); // Bottom Left
            path.lineTo(cx + size, cy + size); // Bottom Right
            path.close();
            canvas.drawPath(path, paint);
        }
    }
}
```

## XML Code

### AndroidManifest.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@android:drawable/sym_def_app_icon"
        android:label="@string/app_name"
        android:roundIcon="@android:drawable/sym_def_app_icon"
        android:supportsRtl="true"
        android:theme="@style/Theme.ShapeApp"
        tools:targetApi="31">
        
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
        <activity
            android:name=".DisplayActivity"
            android:exported="false"
            android:parentActivityName=".MainActivity" />
            
    </application>

</manifest>
```

### strings.xml
```xml
<resources>
    <string name="app_name">Shape App</string>
    <string name="select_shape">Select a Shape:</string>
    <string name="shape_circle">Circle</string>
    <string name="shape_square">Square</string>
    <string name="shape_triangle">Triangle</string>
    <string name="select_color">Select a Color:</string>
    <string name="color_red">Red</string>
    <string name="color_blue">Blue</string>
    <string name="color_green">Green</string>
    <string name="color_yellow">Yellow</string>
    <string name="submit">Submit</string>
    <string name="result_title">Here is your Shape</string>
    <string name="error_selection">Please select both a shape and a color.</string>
</resources>
```

### activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true"
    android:background="?android:attr/colorBackground"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp"
        android:clipToPadding="false">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/app_name"
            android:textSize="32sp"
            android:textStyle="bold"
            android:textColor="?attr/colorPrimary"
            android:gravity="center"
            android:layout_marginTop="24dp"
            android:layout_marginBottom="32dp"/>

        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:cardElevation="4dp"
            app:cardCornerRadius="16dp"
            app:cardUseCompatPadding="true"
            android:layout_marginBottom="16dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="20dp">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="@string/select_shape"
                    android:textSize="18sp"
                    android:textStyle="bold"
                    android:textColor="?android:attr/textColorPrimary"
                    android:layout_marginBottom="16dp"/>

                <RadioGroup
                    android:id="@+id/radioGroupShape"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">

                    <RadioButton
                        android:id="@+id/radioCircle"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/shape_circle" 
                        android:paddingStart="8dp"
                        android:paddingEnd="8dp"
                        android:minHeight="48dp"/>

                    <RadioButton
                        android:id="@+id/radioSquare"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/shape_square" 
                        android:paddingStart="8dp"
                        android:paddingEnd="8dp"
                        android:minHeight="48dp"/>

                    <RadioButton
                        android:id="@+id/radioTriangle"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/shape_triangle" 
                        android:paddingStart="8dp"
                        android:paddingEnd="8dp"
                        android:minHeight="48dp"/>
                </RadioGroup>
            </LinearLayout>
        </com.google.android.material.card.MaterialCardView>

        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:cardElevation="4dp"
            app:cardCornerRadius="16dp"
            app:cardUseCompatPadding="true"
            android:layout_marginBottom="32dp">
            
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="20dp">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="@string/select_color"
                    android:textSize="18sp"
                    android:textStyle="bold"
                    android:textColor="?android:attr/textColorPrimary"
                    android:layout_marginBottom="16dp"/>

                <RadioGroup
                    android:id="@+id/radioGroupColor"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content">

                    <RadioButton
                        android:id="@+id/radioRed"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/color_red" 
                        android:paddingStart="8dp"
                        android:paddingEnd="8dp"
                        android:minHeight="48dp"/>

                    <RadioButton
                        android:id="@+id/radioBlue"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/color_blue" 
                        android:paddingStart="8dp"
                        android:paddingEnd="8dp"
                        android:minHeight="48dp"/>

                    <RadioButton
                        android:id="@+id/radioGreen"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/color_green" 
                        android:paddingStart="8dp"
                        android:paddingEnd="8dp"
                        android:minHeight="48dp"/>

                    <RadioButton
                        android:id="@+id/radioYellow"
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:text="@string/color_yellow" 
                        android:paddingStart="8dp"
                        android:paddingEnd="8dp"
                        android:minHeight="48dp"/>
                </RadioGroup>
            </LinearLayout>
        </com.google.android.material.card.MaterialCardView>

        <com.google.android.material.button.MaterialButton
            android:id="@+id/btnSubmit"
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:text="@string/submit"
            android:textSize="18sp"
            android:textStyle="bold"
            app:cornerRadius="30dp"
            app:elevation="4dp"/>

    </LinearLayout>
</ScrollView>
```

### activity_display.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="24dp"
    android:gravity="center"
    android:background="?android:attr/colorBackground">

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/result_title"
        android:textSize="28sp"
        android:textStyle="bold"
        android:textColor="?attr/colorPrimary"
        android:gravity="center"
        android:layout_marginBottom="48dp"/>

    <com.google.android.material.card.MaterialCardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:cardElevation="8dp"
        app:cardCornerRadius="24dp"
        app:cardUseCompatPadding="true"
        app:strokeWidth="1dp"
        app:strokeColor="#DDDDDD">

        <FrameLayout
            android:id="@+id/shapeContainer"
            android:layout_width="300dp"
            android:layout_height="300dp"
            android:background="#FAFAFA"/>
            
    </com.google.android.material.card.MaterialCardView>
        
</LinearLayout>
```.

OUTPUT :









