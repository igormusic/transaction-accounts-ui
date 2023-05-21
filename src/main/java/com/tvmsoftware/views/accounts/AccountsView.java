package com.tvmsoftware.views.accounts;

import com.tvmsoftware.views.MainLayout;
import com.tvmsoftware.webclient.AccountClient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.openapitools.client.model.AccountInfo;

import java.math.BigDecimal;

@PageTitle("Accounts")
@Route(value = "accounts", layout = MainLayout.class)
public class AccountsView extends VerticalLayout {

    private final AccountClient client;
    private final Grid<AccountInfo> grid = new Grid<>(AccountInfo.class, false);

    public AccountsView(AccountClient client) {
        this.client = client;

        grid.addColumn("accountId").setAutoWidth(true);
        grid.addColumn("active").setAutoWidth(true);
        grid.addColumn( ai-> ai.getAccount().getAccountTypeName()).setHeader("Account Type").setAutoWidth(true);
        grid.addColumn(ai-> ai.getAccount().getStartDate()).setHeader("Start Date").setAutoWidth(true);
        grid.addColumn(ai-> ai.getAccount().getPositions() != null ? ai.getAccount().getPositions().get("Principal") : BigDecimal.ZERO).setHeader("Principal").setAutoWidth(true);
        // Add a custom column with a button
        Grid.Column<AccountInfo> customColumn = grid.addComponentColumn(entity -> {
            Button button = new Button("Edit");
            button.addClickListener(event -> {
                // Handle button click event
                System.out.println("Button clicked for entity with ID: " + entity.getAccountId());
            });
            return button;
        }).setHeader("Actions");

        // Set the column width
        customColumn.setWidth("150px");

        var accounts = client.getAll();

        grid.setItems(accounts);

        add(grid);

    }

}
