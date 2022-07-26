import { Client } from "@stomp/stompjs";
import { reactive, readonly } from "vue";
import type { AngebotListeDing, IAngebotListeItem } from "./IAngebotListeItem";
import type { IBackendInfoMessage } from "./IBackendInfoMessage";

export interface IAngebotState {
    angebotliste: IAngebotListeItem[],
    errormessage: string
}

const angebotState = reactive<IAngebotState>({
    angebotliste:[],
    errormessage:""
});


export function useAngebot(){
    return {angebote :readonly(angebotState), updateAngebote, receiveAngebotMessages}
}

function updateAngebote(): void{
    const url = `/api/angebot`;
    fetch(url).then(resp =>{
        if(!resp.ok){
            throw new Error(resp.statusText);
        }
        return resp.json();
        
    }).then((jsondata:[])=>{
        angebotState.angebotliste = jsondata;
    }

    )
    .catch(fehler =>{
        angebotState.errormessage="Angebote konnten nicht geladen werden";
    }
    )
}
function receiveAngebotMessages(){
    const wsurl = `ws://${window.location.host}/stompbroker`;
    const DEST = "/topic/angebot";
    const stompclient = new Client({ brokerURL: wsurl }) 
    stompclient.onWebSocketError = (event) => { console.log("WS-Fehler")/* WS-Fehler */ } 
    stompclient.onStompError = (frame) => { console.log("STOMP-Fehler")/* STOMP-Fehler */ }
    
    stompclient.onConnect = (frame)=> {
        
        stompclient.subscribe(DEST, (message) =>{
           
            console.log(message.body);

            message = JSON.parse(message.body);
            updateAngebote()
        });
    }
    stompclient.onDisconnect = () => { /* Verbindung abgebaut*/ }
    
    stompclient.activate();
    
}