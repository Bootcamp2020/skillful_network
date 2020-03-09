export class User {
    constructor(data) {
        this.id = data.id;
        this.firstName = data.firstName;
        this.lastName = data.lastName;
        this.birthDate = new Date(data.birthDate);
        this.email = data.email;
        this.mobileNumber = data.mobileNumber;
    }
    /* GETTERS & SETTERS */
    get id() {
        return this._id;
    }
    set id(value) {
        this._id = value;
    }
    get firstName() {
        return this._firstName;
    }
    set firstName(value) {
        this._firstName = value;
    }
    get lastName() {
        return this._lastName;
    }
    set lastName(value) {
        this._lastName = value;
    }
    get birthDate() {
        return this._birthDate;
    }
    set birthDate(value) {
        this._birthDate = value;
    }
    get email() {
        return this._email;
    }
    set email(value) {
        this._email = value;
    }
    get mobileNumber() {
        return this._mobileNumber;
    }
    set mobileNumber(value) {
        this._mobileNumber = value;
    }
}
//# sourceMappingURL=user.js.map