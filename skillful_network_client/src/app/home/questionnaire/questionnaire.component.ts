import { Component, OnInit } from '@angular/core';
import {ExerciceSetService} from '../../shared/services/exerciceSet.service';
import {MatDialog} from "@angular/material/dialog";
import {ModalResultComponent} from "./modal-result/modal-result.component";

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.scss']
})
export class QuestionnaireComponent implements OnInit {

  indexQuestion: number;
  indexQuestionnaire: number;
  public selection: number;
  public disabledButton: boolean;
  public disabledButtonP: boolean;
  nbAllQuestion: number;
  progressionValeur: number;
  public dataReturn: any;

//  Donnée depuis le MOCK
//  public simulation = this.sim.findAllMock();
  public simulation: any;

constructor(public dialog: MatDialog, public sim: ExerciceSetService) {
  }

  ngOnInit(): void {

//  Initialisation Variables
    this.indexQuestion = 0;
    this.indexQuestionnaire = 0;
    this.dataReturn = {exerciseSet: []};
    this.selection = null;
    this.disabledButton = true;
    this.disabledButtonP = true;
    this.progressionValeur = 0;

    this.sim.findAll().then(res => {
      this.simulation = res;
      console.log(this.simulation);

      this.addAnswerSetToDataReturn();

//  Pour avoir le nombre de question total des 2 questionnaires pour calcul progress bar
      this.nbAllQuestion = this.nbQuestion();
      this.progressionValeur += ((1 / this.nbAllQuestion) * 100);
    });

  }

// Calcul du nombre de question totale de tous les questionnaires disponible dans la simulation
  nbQuestion(): number {
  let nbQ = 0;
  for(let cpt = 0; cpt < this.simulation.exerciseSet.length; cpt++) {
    nbQ += this.simulation.exerciseSet[cpt].questions.length;
  }
  return nbQ;
  }

// Désactivation du boutton pour obliger l'utilisateur à répondre à la question
activateButton() {
  this.disabledButton = false;
  this.disabledButtonP = false;
  if(this.indexQuestion === 0)
  {
  this.disabledButtonP = true;
} 

  }
//  Passer à la question suivante, suite au clic de l'utilisateur
next() {
  this.addQuestionToDataReturn();
  this.indexQuestion += 1;
  this.progressionValeur += ((1 / this.nbAllQuestion) * 100);
  this.disabledButton = true;
  if (this.indexQuestion==1){
    this.disabledButtonP = false;
  }
}

//  Passer au questionnaire suivant, suite au clic de l'utilisateur
  nextQcm() {
    this.addQuestionToDataReturn();
    this.indexQuestion = 0;
    this.indexQuestionnaire += 1;
    this.disabledButton = true;
    this.progressionValeur += ((1 / this.nbAllQuestion) * 100);
    this.addAnswerSetToDataReturn();
    if(this.indexQuestionnaire==1 && this.indexQuestion==0){
      this.disabledButtonP = false;
    }
  }
  //  Passer au questionnaire précédent, suite au clic de l'utilisateur
  previousQcm() {
    this.indexQuestion = (this.simulation.exerciseSet[this.indexQuestionnaire-1].questions.length)-1;
    this.indexQuestionnaire -= 1;
    this.progressionValeur -= (1 / this.nbAllQuestion)*100;
    this.dataReturn.exerciseSet.pop();
    this.dataReturn.exerciseSet[this.indexQuestionnaire].answerSet.pop();  
  }
  //  Passer à la question précédente, suite au clic de l'utilisateur
  previous() { 
    this.indexQuestion -= 1;
    this.progressionValeur -= ((1 / this.nbAllQuestion) * 100);
    if(this.indexQuestion === 0 && this.indexQuestionnaire==0)
    {
    this.disabledButtonP = true;
  }  
  this.dataReturn.exerciseSet[this.indexQuestionnaire].answerSet.pop(); 
}
 
//  Validation des questionnaires fait par l'utilisateur, puis envoi au backend pour correction
  submit() {
    this.addQuestionToDataReturn();
    console.log(this.dataReturn);
//    console.log(JSON.stringify(this.dataReturn));
    this.sim.export(this.simulation.id, this.dataReturn).then(res => {
      console.log(res);
      this.openDialog(res);
    });
  }

  openDialog(modalResult): void {
    const dialogRef = this.dialog.open(ModalResultComponent, {
      data: { jobOfferId: modalResult.jobOffer.id, jobOfferName: modalResult.jobOffer.name, jobAccess: modalResult.jobAccess, training: modalResult.training},
    });
  }

//  Rajout de la réponse utilisateur et l'id de la question à l'objet de retour pour le backend
  addQuestionToDataReturn() {
    this.dataReturn.exerciseSet[this.indexQuestionnaire].answerSet.push({questionId: null, answer: null});
    this.dataReturn.exerciseSet[this.indexQuestionnaire].answerSet[this.indexQuestion].questionId = this.simulation.exerciseSet[this.indexQuestionnaire].questions[this.indexQuestion].id;
    this.dataReturn.exerciseSet[this.indexQuestionnaire].answerSet[this.indexQuestion].answer = this.selection;
  }

//  Rajout du nouveau questionnaire et de son l'id à l'objet de retour pour le backend
  addAnswerSetToDataReturn() {
    this.dataReturn.exerciseSet.push({id: null, answerSet: []});
    this.dataReturn.exerciseSet[this.indexQuestionnaire].id = this.simulation.exerciseSet[this.indexQuestionnaire].id;
  }
}
