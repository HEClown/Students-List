package com.example.studentslist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentslist.adapter.StudentAdapter;
import com.example.studentslist.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    final String[] GENDERS = {"Мужской", "Женский"};

    ArrayList<StudentModel> studentList;

    Button addStudentButton;
    Button cancelAddStudentButton;
    Button saveAddStudentButton;

    TextView emptyList;
    TextView firstName;
    TextView profileFirstName;
    TextView secondName;
    TextView profileSecondName;

    View addStudentBlock;
    View profileStudentBlock;
    RecyclerView studentlistRecycler;
    StudentAdapter studentAdapter;
    StudentAdapter.StudentClickListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = new ArrayList<>();

        addStudentBlock = (View) findViewById(R.id.block_add_student);
        profileStudentBlock = findViewById(R.id.block_profile_student);
        firstName = (TextView) findViewById(R.id.student_first_name);
        secondName = (TextView) findViewById(R.id.student_second_name);
        addStudentButton = (Button) findViewById(R.id.button_add_student);
        cancelAddStudentButton = (Button) findViewById(R.id.button_cancel_add_student);
        saveAddStudentButton = (Button) findViewById(R.id.button_save_add_student);

        createSpinnerGenders();

        // Обработка нажатия для появление блока добавления студента
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileStudentBlock.setVisibility(View.GONE);
                addStudentBlock.setVisibility(View.VISIBLE);
            }
        });

        // Обработка нажатия для исчезания блока добавления студента и очистка полей
        cancelAddStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudentBlock.setVisibility(View.GONE);
                firstName.setText("");
                secondName.setText("");
            }
        });

        // Обработка нажатия для добавления студента в список
        saveAddStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEditTextsAndAddStudent();
                checkLengthOfList();
            }
        });

        setStudentRecycler(studentList);

    }

    // Обработка нажатия на студента в списке
    // При нажатии отображается профиль студента
    private void setOnClickListener() {
        listener = new StudentAdapter.StudentClickListener() {
            @Override
            public void onClick(View view, int position) {
                addStudentBlock = (View) findViewById(R.id.block_add_student);
                addStudentBlock.setVisibility(View.GONE);

                profileStudentBlock = (View) findViewById(R.id.block_profile_student);
                profileStudentBlock.setVisibility(View.VISIBLE);

                profileFirstName = (TextView) findViewById(R.id.profile_student_first_name);
                profileFirstName.setText("123");

                profileSecondName = (TextView) findViewById(R.id.profile_student_second_name);
                profileSecondName.setText("321");
            }
        };
    }

    // Установка списка и способа его отображения
    private void setStudentRecycler(List<StudentModel> studentList) {
        setOnClickListener();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        studentlistRecycler = findViewById(R.id.rv_student_list);
        studentlistRecycler.setLayoutManager(layoutManager);

        studentAdapter = new StudentAdapter(this, studentList, listener);
        studentlistRecycler.setAdapter(studentAdapter);

    }

    // Метод для проверки полей и добавления студента в список
    // Список пополняется только в случае, если поля не будут пустыми
    private void checkEditTextsAndAddStudent() {
        if (!(firstName.getText().toString().equals("")) && !(secondName.getText().toString().equals(""))) {
            studentList.add(new StudentModel(studentList.size() + 1, firstName.getText().toString(), secondName.getText().toString()));
            firstName.setText("");
            secondName.setText("");
        }
    }

    // Метод для проверки на отсутствие элементов в списке
    // Если список пуст выводится надпись "Здесь пока пусто"
    // Если нет, то отображаем список
    private void checkLengthOfList() {
        studentlistRecycler = findViewById(R.id.rv_student_list);
        emptyList = findViewById(R.id.tv_empty_list);
        if (studentList.size() == 0) {
            studentlistRecycler.setVisibility(View.GONE);
            emptyList.setVisibility(View.VISIBLE);
        } else {
            studentList.size();
            emptyList.setVisibility(View.GONE);
            studentlistRecycler.setVisibility(View.VISIBLE);
        }
    }

    // Создание выпадающего списка
    private void createSpinnerGenders() {
        // Создание адаптера для выпадающего списка и определение стилей отображения
        ArrayAdapter<String> gendersAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, GENDERS);
        gendersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Создание выпадающего списка
        Spinner gendersSpinner = (Spinner) findViewById(R.id.spinner_genders);
        // Установка адаптера для выпадающего списка
        gendersSpinner.setAdapter(gendersAdapter);
    }

}