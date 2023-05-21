package com.tvmsoftware.views.accounttypes;

import com.tvmsoftware.views.MainLayout;
import com.tvmsoftware.webclient.AccountTypeClient;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.openapitools.client.model.AccountType;

@PageTitle("Account Types")
@Route(value = "account-types", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class AccountTypesView extends VerticalLayout {

    private final AccountTypeClient client;
    private final Grid<AccountType> grid = new Grid<>(AccountType.class, false);

    public AccountTypesView(AccountTypeClient client) {
        this.client = client;

        grid.addColumn("name").setAutoWidth(true);
        grid.addColumn("label").setAutoWidth(true);

        var accountTypes = client.getAll();

        grid.setItems(accountTypes);

        add(grid);

    }

}
