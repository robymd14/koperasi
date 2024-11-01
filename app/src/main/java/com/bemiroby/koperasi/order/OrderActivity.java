package com.bemiroby.koperasi.order;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.bemiroby.koperasi.R;
import com.bemiroby.koperasi.utils.FunctionHelper;
import com.google.android.material.button.MaterialButton;

public class OrderActivity extends AppCompatActivity {

    public static final String DATA_TITLE = "TITLE";
    String strTitle;
    int paket1 = 55000, paket2 = 55000, paket3 = 65000, paket4 = 130000, paket5 = 65000, paket6 = 160000;
    int itemCount1 = 0, itemCount2 = 0, itemCount3 = 0, itemCount4 = 0, itemCount5 = 0, itemCount6 = 0;
    int countP1, countP2, countP3, countP4, countP5, countP6, totalItems, totalPrice;
    ImageView imageAdd1, imageAdd2, imageAdd3, imageAdd4, imageAdd5, imageAdd6,
            imageMinus1, imageMinus2, imageMinus3, imageMinus4, imageMinus5, imageMinus6;
    Toolbar toolbar;
    TextView tvPaket1, tvPaket2, tvPaket3, tvPaket4, tvPaket5, tvPaket6,
            tvPaket11, tvJumlahPorsi, tvTotalPrice;
    MaterialButton btnCheckout;
    OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setStatusbar();
        setInitLayout();
        setupPakets();
        setInputData();
    }

    private void setInitLayout() {
        tvPaket11 = findViewById(R.id.tvPaket11);
        toolbar = findViewById(R.id.toolbar);
        tvPaket1 = findViewById(R.id.tvPaket1);
        tvPaket2 = findViewById(R.id.tvPaket2);
        tvPaket3 = findViewById(R.id.tvPaket3);
        tvPaket4 = findViewById(R.id.tvPaket4);
        tvPaket5 = findViewById(R.id.tvPaket5);
        tvPaket6 = findViewById(R.id.tvPaket6);
        tvJumlahPorsi = findViewById(R.id.tvJumlahPorsi);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        imageAdd1 = findViewById(R.id.imageAdd1);
        imageAdd2 = findViewById(R.id.imageAdd2);
        imageAdd3 = findViewById(R.id.imageAdd3);
        imageAdd4 = findViewById(R.id.imageAdd4);
        imageAdd5 = findViewById(R.id.imageAdd5);
        imageAdd6 = findViewById(R.id.imageAdd6);
        imageMinus1 = findViewById(R.id.imageMinus1);
        imageMinus2 = findViewById(R.id.imageMinus2);
        imageMinus3 = findViewById(R.id.imageMinus3);
        imageMinus4 = findViewById(R.id.imageMinus4);
        imageMinus5 = findViewById(R.id.imageMinus5);
        imageMinus6 = findViewById(R.id.imageMinus6);
        btnCheckout = findViewById(R.id.btnCheckout);

        // Handling title safely
        if (getIntent().getExtras() != null) {
            strTitle = getIntent().getExtras().getString(DATA_TITLE);
        }

        if (strTitle != null) {
            setSupportActionBar(toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(strTitle);
        }

        tvPaket11.setPaintFlags(tvPaket11.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
    }

    private void setupPakets() {
        setupPaket(1, imageAdd1, imageMinus1, tvPaket1, paket1);
        setupPaket(2, imageAdd2, imageMinus2, tvPaket2, paket2);
        setupPaket(3, imageAdd3, imageMinus3, tvPaket3, paket3);
        setupPaket(4, imageAdd4, imageMinus4, tvPaket4, paket4);
        setupPaket(5, imageAdd5, imageMinus5, tvPaket5, paket5);
        setupPaket(6, imageAdd6, imageMinus6, tvPaket6, paket6);
    }

    private void setupPaket(int paketIndex, ImageView addButton, ImageView minusButton, TextView paketTextView, int paketPrice) {
        addButton.setOnClickListener(v -> {
            incrementItemCount(paketIndex);
            paketTextView.setText(String.valueOf(getItemCount(paketIndex)));
            setTotalPrice();
        });

        minusButton.setOnClickListener(v -> {
            if (getItemCount(paketIndex) > 0) {
                decrementItemCount(paketIndex);
                paketTextView.setText(String.valueOf(getItemCount(paketIndex)));
                setTotalPrice();
            }
        });
    }

    private int getItemCount(int paketIndex) {
        switch (paketIndex) {
            case 1: return itemCount1;
            case 2: return itemCount2;
            case 3: return itemCount3;
            case 4: return itemCount4;
            case 5: return itemCount5;
            case 6: return itemCount6;
            default: return 0;
        }
    }

    private void incrementItemCount(int paketIndex) {
        switch (paketIndex) {
            case 1: itemCount1++; break;
            case 2: itemCount2++; break;
            case 3: itemCount3++; break;
            case 4: itemCount4++; break;
            case 5: itemCount5++; break;
            case 6: itemCount6++; break;
        }
    }

    private void decrementItemCount(int paketIndex) {
        switch (paketIndex) {
            case 1: itemCount1--; break;
            case 2: itemCount2--; break;
            case 3: itemCount3--; break;
            case 4: itemCount4--; break;
            case 5: itemCount5--; break;
            case 6: itemCount6--; break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void setTotalPrice() {
        totalItems = itemCount1 + itemCount2 + itemCount3 + itemCount4 + itemCount5 + itemCount6;
        totalPrice = (paket1 * itemCount1) + (paket2 * itemCount2) + (paket3 * itemCount3) +
                (paket4 * itemCount4) + (paket5 * itemCount5) + (paket6 * itemCount6);

        tvJumlahPorsi.setText(totalItems + " items");
        tvTotalPrice.setText(FunctionHelper.rupiahFormat(totalPrice));
    }

    private void setInputData() {
        btnCheckout.setOnClickListener(v -> {
            if (totalItems == 0 || totalPrice == 0) {
                Toast.makeText(OrderActivity.this, "Ups, pilih minuman dulu!",
                        Toast.LENGTH_SHORT).show();
            } else if (totalItems == 0) {
                Toast.makeText(OrderActivity.this, "Silahkan klik pesan sekarang!",
                        Toast.LENGTH_SHORT).show();
            } else {
                orderViewModel.addDataOrder(strTitle, totalItems, totalPrice);
                Toast.makeText(OrderActivity.this,
                        "Yeay! Pesanan Anda sedang diproses, cek di menu riwayat ya!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void setStatusbar() {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(@NonNull Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
