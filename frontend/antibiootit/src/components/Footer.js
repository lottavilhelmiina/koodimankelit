import { NavLink } from "react-router-dom";

export default function Footer() {
    return (
        <footer>
            <NavLink to="/tietoa" state="footer-disclaimer" className="footer--lauseke" >Vastuuvapauslauseke</NavLink>
            <NavLink to="/tietoa" state="footer-makers" className="footer--tekijat" >Tekijät</NavLink>
        </footer>
    );
}
