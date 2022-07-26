<template>
    
    <h3>Wir haben aktuell {{angebote.angebotliste.length}} Angebote für Sie</h3>
    <AngebotListe/>
    <button v-on:click="updateAngebote">update</button>
    <div v-if="angebote.errormessage !=''">
        <p>{{angebote.errormessage}}</p>
    </div>

    
</template>

<script setup lang="ts">
import AngebotListe from '@/components/AngebotListe.vue';
import { useAngebot } from '@/services/useAngebot';
import { onMounted } from 'vue';

const {angebote, updateAngebote, receiveAngebotMessages} = useAngebot();

onMounted(async()=>{
    receiveAngebotMessages();
    updateAngebote();
    
});
</script>