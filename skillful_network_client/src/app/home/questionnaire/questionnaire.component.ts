import { Component, OnInit } from '@angular/core';
import {ExerciceSetService} from '../../shared/services/exerciceSet.service';
import {ReturnExerciceSet} from '../../shared/models/return-exerciceSet';
import {ReturnAnswerSet} from '../../shared/models/return-answerSet';
import {ReturnQuestion} from '../../shared/models/return-question';

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.scss']
})
export class QuestionnaireComponent implements OnInit {

  indexQuestion: number;
  indexQuestionnaire: number;
  start = true;
  returnQuest: any;
  returnAnsw: any[];
  public returnExo: any[];
  public selection: number;
  public disabledButton: boolean;
  nbAllQuestion: number;
  progressionValeur: number;

//  Donnée depuis le MOCK
  public simulation = this.sim.findAllMock();

constructor(public sim: ExerciceSetService) {
  }

  ngOnInit(): void {
    console.log(this.simulation);
//  Initialisation Variables
    this.indexQuestion = 0;
    this.indexQuestionnaire = 0;
    this.returnAnsw = [];
    this.returnExo = [];
    this.selection = null;
    this.disabledButton = true;
    this.progressionValeur = 0;

//  Pour avoir le nombre de question total des 2 questionnaires pour calcul progress bar
    this.nbAllQuestion = this.nbQuestion();

// Création de l'objet réponse utilisateur pour le retour au backend
    this.returnQuest = new ReturnQuestion(0, 0);
    this.returnAnsw.push(new ReturnAnswerSet(1, this.returnQuest));
    this.returnExo.push(new ReturnExerciceSet(this.returnAnsw));
    console.log(this.returnExo);

    this.progressionValeur += ((1 / this.nbAllQuestion) * 100);
  }

  nbQuestion(): number {
  let nbQ = 0;
  for(let cpt = 0; cpt < this.simulation[0].exerciceSet.length; cpt++) {
    nbQ += this.simulation[0].exerciceSet[cpt].questions.length;
  }
  return nbQ;
  }

  activateButton() {
  this.disabledButton = false;
  }

  nextQcm() {
    this.indexQuestion = 0;
    this.indexQuestionnaire += 1;
    this.disabledButton = true;
    this.progressionValeur += ((1 / this.nbAllQuestion) * 100);
  }

  next() {
  if (this.indexQuestion === 0) {
    //this.returnExo[0].exerciceSet[this.indexQuestionnaire].answerSet[this.indexQuestion].questionId(5);//(this.simulation[0].exerciceSet[this.indexQuestionnaire].questions[this.indexQuestion].id);
    //this.returnExo[0].exerciceSet[this.indexQuestionnaire].answerSet[this.indexQuestion].answer = this.selection;
    console.log(this.returnExo);
  } else {
    console.log(this.returnExo);
  }
  this.indexQuestion += 1;
    this.progressionValeur += ((1 / this.nbAllQuestion) * 100);
  this.disabledButton = true;
  }
  submit() {
/*
    if(this.currentIndex+1==this.questions.length){
      this.gameover=true;
      this.start=false;
      this.correct=0;
      this.notAttempted=0;
      this.questions.map(x=>{
        if(x.selected!=0){
          if(x.selected == x.answer)
            this.correct=this.correct + 1;
        }
        else {
          this.notAttempted = this.notAttempted + 1;
        }
        x.selected=0;
      });
    }

*/
  }
}
