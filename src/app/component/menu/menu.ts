export interface Menu{
    id?:string;
    titre?:string;
    icon?:string;
    url?:string;
    active?:boolean;
    role?:string;
    sousMenu?:Array<Menu>;
}