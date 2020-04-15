export class Result {
    
    private _id: number;
    private _idExercise: number;
	private _gradeExercise: number;
    
    constructor(data: any) {
        this._id = data.id;
        this._idExercise = data.idExercise;
        this._gradeExercise = data.gradeExercise;
    }
    
    get id(): number { return this._id; }
    set id(value: number) { this._id = value; }
    get idExercise(): number { return this._idExercise; }
    set idExercise(value: number) { this._idExercise = value; }
    get gradeExercise(): number { return this._gradeExercise; }
    set gradeExercise(value: number) { this._gradeExercise = value; }
}
