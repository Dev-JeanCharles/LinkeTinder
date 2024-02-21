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

    void setName(String name) {
        this.name = name
    }

    String getEmail() {
        return email
    }

    void setEmail(String email) {
        this.email = email
    }

    String getState() {
        return state
    }

    void setState(String state) {
        this.state = state
    }

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        this.cep = cep
    }

    @Override
    void Nope() {

    }

    @Override
    void Like() {

    }
}
