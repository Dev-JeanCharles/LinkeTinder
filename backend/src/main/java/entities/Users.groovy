package entities

import repository.UserMethods

class Users implements UserMethods {

    String name,email, state,cep

    Users() {
    }

    Users(String name, String email, String state, String cep) {
        this.name = name
        this.email = email
        this.state = state
        this.cep = cep
    }

    String getName() {
        return name
    }
    String getEmail() {
        return email
    }

    String getState() {
        return state
    }

    String getCep() {
        return cep
    }

    @Override
    void Nope() {

    }

    @Override
    void Like() {

    }
}
