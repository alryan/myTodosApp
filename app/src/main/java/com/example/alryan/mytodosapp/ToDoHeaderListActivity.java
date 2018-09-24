package com.example.alryan.mytodosapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.alryan.mytodosapp.Adapters.ToDoHeaderAdapter;
import com.example.alryan.mytodosapp.DialogFragments.AddEditToDoDialogFragment;
import com.example.alryan.mytodosapp.DialogFragments.SelectActionTodoDialogFragment;
import com.example.alryan.mytodosapp.Interfaces.DialogToDoAddEditListener;
import com.example.alryan.mytodosapp.Interfaces.SelectActionListener;
import com.example.alryan.mytodosapp.Interfaces.ListItemClickListener;
import com.example.alryan.mytodosapp.Models.ToDo;
import com.example.alryan.mytodosapp.Models.ToDoHeader;
import com.example.alryan.mytodosapp.Utility.Utility;

import java.util.ArrayList;
import java.util.Calendar;


public class ToDoHeaderListActivity extends AppCompatActivity
{
    private ArrayList<ToDoHeader> toDoHeaderArrayList = new ArrayList<>();
    private ToDoHeaderAdapter toDoHeaderAdapter;
    private Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        try
        {
            initializeViews();
            createToDos();
        }
        catch (Exception err)
        {
            Utility.showError(getSupportFragmentManager(), "Error ToDoHeaderListActivity onCreate: \n" + err.toString());
        }
    }

    private void initializeViews() throws Exception
    {
        try
        {
            setContentView(R.layout.todo_header_list_activity);

            // Tool bar
            Toolbar tbToDoHeaderList = findViewById(R.id.tbToDoHeaderList);
            tbToDoHeaderList.setTitle("Todos");
            setSupportActionBar(tbToDoHeaderList);

            initializeRecyclerView();
            initializeFab();
        }
        catch (Exception err)
        {
            throw new Exception("Error initializing views: \n" + err.toString());
        }
    }

    private void initializeRecyclerView() throws Exception
    {
        try
        {
            RecyclerView rvToDos = findViewById(R.id.rvToDoHeaders);
            rvToDos.setLayoutManager(new LinearLayoutManager(this));
            rvToDos.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            // Set adapter
            ListItemClickListener listItemClickListener = new ListItemClickListener()
            {
                @Override
                public void onClick(final int position)
                {
                    try
                    {
                        showSelectActionTodoDialog(position);
                    }
                    catch (Exception err)
                    {
                        Utility.showError(getSupportFragmentManager(), "Error initializing recycler view listItemClickListener onClick: \n" + err.toString());
                    }
                }
            };
            toDoHeaderAdapter = new ToDoHeaderAdapter(toDoHeaderArrayList, listItemClickListener);

            rvToDos.setAdapter(toDoHeaderAdapter);
        }
        catch (Exception err)
        {
            throw new Exception("Error initializing Recycler View: \n" + err.toString());
        }
    }

    private void showSelectActionTodoDialog(final int position)
    {
        SelectActionTodoDialogFragment selectActionTodoDialogFragment = new SelectActionTodoDialogFragment();

        if (position > 0)
        {
            Bundle bundle = new Bundle();
            bundle.putParcelable("SELECTED_TODO", toDoHeaderArrayList.get(position));
            selectActionTodoDialogFragment.setArguments(bundle);
        }

        selectActionTodoDialogFragment.setSelectActionListener(new SelectActionListener()
        {
            @Override
            public void onClick(String action)
            {
                try
                {
                    switch (action)
                    {
                        case "Edit":
                            showAddEditToDoDialog(position);
                            break;
                        case "Delete":
                            toDoHeaderArrayList.remove(position);
                            toDoHeaderAdapter.notifyDataSetChanged();
                    }
                }
                catch (Exception err)
                {
                    Utility.showError(getSupportFragmentManager(), "Error showSelectActionTodoDialog selectActionTodoDialogFragment onClick: \n" + err.toString());
                }
            }
        });

        selectActionTodoDialogFragment.show(getSupportFragmentManager(), "SELECT_ACTION_TODO_DIALOG");
    }

    private void initializeFab() throws Exception
    {
        try
        {
            FloatingActionButton fabAddEditToDo = findViewById(R.id.fabAddToDo);

            fabAddEditToDo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    try
                    {
                        showAddEditToDoDialog(0);
                    }
                    catch (Exception err)
                    {
                        Utility.showError(getSupportFragmentManager(), "Error fabAddEditToDo onClick: \n" + err.toString());
                    }
                }
            });
        }
        catch (Exception err)
        {
            throw new Exception("Error initializing Floating Action Button: \n" + err.toString());
        }
    }

    private void showAddEditToDoDialog(final int position) throws Exception
    {
        try
        {
            AddEditToDoDialogFragment addEditToDoDialogFragment = new AddEditToDoDialogFragment();

            // Pass argument
//            if (position > 0)
//            {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("SELECTED_TODO", toDoHeaderArrayList.get(position));
//                addEditToDoDialogFragment.setArguments(bundle);
//            }

            // Set listener
            addEditToDoDialogFragment.setDialogToDoAddEditListener(new DialogToDoAddEditListener()
            {
                @Override
                public void onFinish(ToDo toDo)
                {
                    if (toDo.getId() == 0)
                    {
                        toDo.setId(toDoHeaderArrayList.size());
//                        toDoHeaderArrayList.add(toDo);
                    }
                    else
                    {
//                        toDoHeaderArrayList.get(position).setTodo(toDo.getTodo());
                    }

                    toDoHeaderAdapter.notifyDataSetChanged();
                }
            });

            addEditToDoDialogFragment.show(getSupportFragmentManager(), "ADD_EDIT_TODO_DIALOG");
        }
        catch (Exception err)
        {
            throw new Exception("Error showing to do dialog: \n" + err.toString());
        }
    }

    private void createToDos() throws Exception
    {
        try
        {
            ToDoHeader toDoHeader1 = new ToDoHeader();
            toDoHeader1.setId(1);
            toDoHeader1.setTitle("Todos 1");
            toDoHeader1.setCreatedOn(calendar.getTime());
            toDoHeader1.setLastUpdate(calendar.getTime());
            toDoHeaderArrayList.add(toDoHeader1);

            ToDoHeader toDoHeader2 = new ToDoHeader();
            toDoHeader2.setId(2);
            toDoHeader2.setTitle("Todos 2");
            toDoHeader2.setCreatedOn(calendar.getTime());
            toDoHeader2.setLastUpdate(calendar.getTime());
            toDoHeaderArrayList.add(toDoHeader2);

            ToDoHeader toDoHeader3 = new ToDoHeader();
            toDoHeader3.setId(3);
            toDoHeader3.setTitle("Todos 3");
            toDoHeader3.setCreatedOn(calendar.getTime());
            toDoHeader3.setLastUpdate(calendar.getTime());
            toDoHeaderArrayList.add(toDoHeader3);
        }
        catch (Exception err)
        {
            throw new Exception("Error creating default todo headers: \n" + err.toString());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.todo_header_list_menu, menu);

        // To dos search view
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s)
            {
                try
                {
                    createToDos();
                    toDoHeaderSearch(s);
                }
                catch (Exception err)
                {
                    Utility.showError(getSupportFragmentManager(), "Error onCreateOptionsMenu searchView onQueryTextSubmit: \n" + err.toString());
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                try
                {
                    createToDos();
                    toDoHeaderSearch(s);
                }
                catch (Exception err)
                {
                    Utility.showError(getSupportFragmentManager(), "Error onCreateOptionsMenu searchView onQueryTextChange: \n" + err.toString());
                }

                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void toDoHeaderSearch(String search)
    {
        Utility.print("toDoHeaderSearch");
        Utility.print(search);

        try
        {
            ArrayList<ToDoHeader> toDoHeaderArrayList = new ArrayList<>();

            for(ToDoHeader toDoHeader : this.toDoHeaderArrayList)
            {
                if (toDoHeader.getTitle().contains(search))
                {
                    toDoHeaderArrayList.add(toDoHeader);
                }
            }

            this.toDoHeaderArrayList = toDoHeaderArrayList;

            Utility.print(this.toDoHeaderArrayList.size());

            for(ToDoHeader toDoHeader : this.toDoHeaderArrayList)
            {
                Utility.print(toDoHeader);
            }

            toDoHeaderAdapter.notifyDataSetChanged();
        }
        catch (Exception err)
        {
            Utility.showError(getSupportFragmentManager(), "Error searching on todo headers: \n" + err.toString());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
//            case R.id.action_settings:
//                // User chose the "Settings" item, show the app settings UI...
//                Utility.print("Settings");
//                return true;
//
//            case R.id.action_favorite:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                Utility.print("Favorite");
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Utility.print("Default");
                return super.onOptionsItemSelected(item);

        }
    }
}